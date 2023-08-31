package com.proekt.dnick.aldehyde.web.controller;

import com.proekt.dnick.aldehyde.configs.constants.Pages;
import com.proekt.dnick.aldehyde.service.FurnitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FurnitureService furnitureService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("furnitures", furnitureService.getPopularFurnitures());
        return Pages.HOME;
    }
}
