package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.model.Furniture;

import java.util.List;

public interface CartService {

    List<Furniture> getFurnituresInCart();

    void addFurnitureToCart(Long furnitureId);

    void removeFurnitureFromCart(Long furnitureId);
}
