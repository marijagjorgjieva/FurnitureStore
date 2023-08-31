package com.proekt.dnick.aldehyde.web.controller;

import com.proekt.dnick.aldehyde.configs.constants.Pages;
import com.proekt.dnick.aldehyde.configs.constants.PathConstants;
import com.proekt.dnick.aldehyde.model.dto.request.FurnitureRequest;
import com.proekt.dnick.aldehyde.model.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.model.dto.response.UserInfoResponse;
import com.proekt.dnick.aldehyde.service.AdminService;
import com.proekt.dnick.aldehyde.configs.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(PathConstants.ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/furnitures")
    public String getFurnitures(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getFurnitures(pageable));
        return Pages.ADMIN_FURNITURES;
    }

    @GetMapping("/furnitures/search")
    public String searchFurnitures(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchFurnitures(request, pageable));
        return Pages.ADMIN_FURNITURES;
    }

    @GetMapping("/users")
    public String getUsers(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getUsers(pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/users/search")
    public String searchUsers(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchUsers(request, pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", adminService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping("/orders")
    public String getOrders(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getOrders(pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/orders/search")
    public String searchOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/furniture/{furnitureId}")
    public String getFurniture(@PathVariable Long furnitureId, Model model) {
        model.addAttribute("furniture", adminService.getFurnitureById(furnitureId));
        return Pages.ADMIN_EDIT_FURNITURE;
    }

    @PostMapping("/edit/furniture")
    public String editFurniture(@Valid FurnitureRequest furniture, BindingResult bindingResult, Model model,
                              @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "furniture", furniture)) {
            return Pages.ADMIN_EDIT_FURNITURE;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/furnitures", adminService.editFurniture(furniture, file));
    }

    @GetMapping("/add/furniture")
    public String addFurniture() {
        return Pages.ADMIN_ADD_FURNITURE;
    }

    @PostMapping("/add/furniture")
    public String addFurniture(@Valid FurnitureRequest furniture, BindingResult bindingResult, Model model,
                             @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "furniture", furniture)) {
            return Pages.ADMIN_ADD_FURNITURE;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/furnitures", adminService.addFurniture(furniture, file));
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable Long userId, Model model, Pageable pageable) {
        UserInfoResponse userResponse = adminService.getUserById(userId, pageable);
        model.addAttribute("user", userResponse.getUser());
        controllerUtils.addPagination(model, userResponse.getOrders());
        return Pages.ADMIN_USER_DETAIL;
    }
}
