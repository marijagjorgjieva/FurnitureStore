package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.model.Order;
import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.dto.request.FurnitureRequest;
import com.proekt.dnick.aldehyde.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.dto.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    Page<Furniture> getFurnitures(Pageable pageable);

    Page<Furniture> searchFurnitures(SearchRequest request, Pageable pageable);

    Page<User> getUsers(Pageable pageable);

    Page<User> searchUsers(SearchRequest request, Pageable pageable);

    Order getOrder(Long orderId);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(SearchRequest request, Pageable pageable);

    Furniture getFurnitureById(Long furnitureId);

    MessageResponse editFurniture(FurnitureRequest furnitureRequest, MultipartFile file);

    MessageResponse addFurniture(FurnitureRequest furnitureRequest, MultipartFile file);

    UserInfoResponse getUserById(Long userId, Pageable pageable);
}
