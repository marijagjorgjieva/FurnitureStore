package com.proekt.dnick.aldehyde.controller;

import com.proekt.dnick.aldehyde.constants.Pages;
import com.proekt.dnick.aldehyde.constants.PathConstants;
import com.proekt.dnick.aldehyde.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.CART)
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("furnitures", cartService.getFurnituresInCart());
        return Pages.CART;
    }

    @PostMapping("/add")
    public String addFurnitureToCart(@RequestParam("furnitureId") Long furnitureId) {
        cartService.addFurnitureToCart(furnitureId);
        return "redirect:" + PathConstants.CART;
    }

    @PostMapping("/remove")
    public String removeFurnitureFromCart(@RequestParam("furnitureId") Long furnitureId) {
        cartService.removeFurnitureFromCart(furnitureId);
        return "redirect:" + PathConstants.CART;
    }
}
