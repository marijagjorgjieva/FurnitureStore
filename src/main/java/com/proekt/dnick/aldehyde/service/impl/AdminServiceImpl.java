package com.proekt.dnick.aldehyde.service.impl;

import com.proekt.dnick.aldehyde.configs.constants.ErrorMessage;
import com.proekt.dnick.aldehyde.configs.constants.SuccessMessage;
import com.proekt.dnick.aldehyde.model.Order;
import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.model.dto.request.FurnitureRequest;
import com.proekt.dnick.aldehyde.model.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.model.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.model.dto.response.UserInfoResponse;
import com.proekt.dnick.aldehyde.repository.OrderRepository;
import com.proekt.dnick.aldehyde.repository.FurnitureRepository;
import com.proekt.dnick.aldehyde.repository.UserRepository;
import com.proekt.dnick.aldehyde.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${upload.path}")
   private String uploadPath;

    private final UserRepository userRepository;
    private final FurnitureRepository furnitureRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Furniture> getFurnitures(Pageable pageable) {
        return furnitureRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Furniture> searchFurnitures(SearchRequest request, Pageable pageable) {
        return furnitureRepository.searchFurnitures(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchUsers(SearchRequest request, Pageable pageable) {
        return userRepository.searchUsers(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.getById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);

    }

    @Override
    public Page<Order> searchOrders(SearchRequest request, Pageable pageable) {
        return orderRepository.searchOrders(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Furniture getFurnitureById(Long furnitureId) {
        return furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.FURNITURE_NOT_FOUND));
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse editFurniture(FurnitureRequest furnitureRequest, MultipartFile file) {
        return saveFurniture(furnitureRequest, file, SuccessMessage.FURNITURE_EDITED);
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse addFurniture(FurnitureRequest furnitureRequest, MultipartFile file) {
        return saveFurniture(furnitureRequest, file, SuccessMessage.FURNITURE_ADDED);
    }

    @Override
    public UserInfoResponse getUserById(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND));
        Page<Order> orders = orderRepository.findOrderByUserId(userId, pageable);
        return new UserInfoResponse(user, orders);
    }

    private MessageResponse saveFurniture(FurnitureRequest furnitureRequest, MultipartFile file, String message) throws IOException {
        Furniture furniture = modelMapper.map(furnitureRequest, Furniture.class);

        String rootPath = System.getProperty("user.dir");
        String uploadPath = rootPath + "/src/main/resources/uploads";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
           // File uploadDir = new File(uploadPath);

          //  if (!uploadDir.exists()) {
              //  uploadDir.mkdir();
            //}
            String resultFilename = file.getOriginalFilename();
            //file.transferTo(new File(uploadPath + "/" + resultFilename));
            furniture.setFilename("слика.jpg");

        }
        furnitureRepository.save(furniture);
        return new MessageResponse("alert-success", message);
    }

}
