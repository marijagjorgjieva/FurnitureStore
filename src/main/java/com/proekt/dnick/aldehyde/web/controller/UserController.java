package com.proekt.dnick.aldehyde.web.controller;

import com.proekt.dnick.aldehyde.configs.constants.Pages;
import com.proekt.dnick.aldehyde.configs.constants.PathConstants;
import com.proekt.dnick.aldehyde.model.dto.request.ChangePasswordRequest;
import com.proekt.dnick.aldehyde.model.dto.request.EditUserRequest;
import com.proekt.dnick.aldehyde.model.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.model.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.service.UserService;
import com.proekt.dnick.aldehyde.configs.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.USER)
public class UserController {

    private final UserService userService;
    private final ControllerUtils controllerUtils;




    @GetMapping("/account")
    public String userAccount(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return Pages.USER_ACCOUNT;
    }

    @GetMapping("/orders/search")
    public String searchUserOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, userService.searchUserOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/info")
    public String userInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return Pages.USER_INFO;
    }

    @GetMapping("/info/edit")
    public String editUserInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return Pages.USER_INFO_EDIT;
    }

    @PostMapping("/info/edit")
    public String editUserInfo(@Valid EditUserRequest request, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Model model) {
        if (controllerUtils.validateInputFields(bindingResult, model, "user", request)) {
            return Pages.USER_INFO_EDIT;
        }
        MessageResponse messageResponse = userService.editUserInfo(request);
        return controllerUtils.setAlertFlashMessage(redirectAttributes, "/user/info", messageResponse);
    }


}
