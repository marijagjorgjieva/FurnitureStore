package com.proekt.dnick.aldehyde.service.impl;

import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.repository.FurnitureRepository;
import com.proekt.dnick.aldehyde.service.CartService;
import com.proekt.dnick.aldehyde.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final FurnitureRepository furnitureRepository;

    @Override
    public List<Furniture> getFurnituresInCart() {
        User user = userService.getAuthenticatedUser();
        return user.getFurnitureList();
    }

    @Override
    @Transactional
    public void addFurnitureToCart(Long furnitureId) {
        User user = userService.getAuthenticatedUser();
        Furniture furniture = furnitureRepository.getOne(furnitureId);
        user.getFurnitureList().add(furniture);
    }

    @Override
    @Transactional
    public void removeFurnitureFromCart(Long furnitureId) {
        User user = userService.getAuthenticatedUser();
        Furniture furniture = furnitureRepository.getOne(furnitureId);
        user.getFurnitureList().remove(furniture);
    }
}
