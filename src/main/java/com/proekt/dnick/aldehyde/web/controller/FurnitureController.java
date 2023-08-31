package com.proekt.dnick.aldehyde.web.controller;

import com.proekt.dnick.aldehyde.configs.constants.Pages;
import com.proekt.dnick.aldehyde.configs.constants.PathConstants;
import com.proekt.dnick.aldehyde.model.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.service.FurnitureService;
import com.proekt.dnick.aldehyde.configs.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.FURNITURE)
public class FurnitureController {

    private final FurnitureService furnitureService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{furnitureId}")
    public String getFurnitureById(@PathVariable Long furnitureId, Model model) {
        model.addAttribute("furniture", furnitureService.getFurnitureById(furnitureId));
        return Pages.FURNITURE;
    }

    @GetMapping
    public String getFurnituresByFilterParams(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, furnitureService.getFurnituresByFilterParams(request, pageable));
        return Pages.FURNITURES;
    }

    @GetMapping("/search")
    public String searchFurnitures(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, furnitureService.searchFurnitures(request, pageable));
        return Pages.FURNITURES;
    }

}
