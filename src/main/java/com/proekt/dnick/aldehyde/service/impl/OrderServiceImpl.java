package com.proekt.dnick.aldehyde.service.impl;

import com.proekt.dnick.aldehyde.constants.ErrorMessage;
import com.proekt.dnick.aldehyde.model.Order;
import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.dto.request.OrderRequest;
import com.proekt.dnick.aldehyde.repository.OrderRepository;
import com.proekt.dnick.aldehyde.service.OrderService;
import com.proekt.dnick.aldehyde.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Order getOrder(Long orderId) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.getByIdAndUserId(orderId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Furniture> getOrdering() {
        User user = userService.getAuthenticatedUser();
        return user.getFurnitureList();
    }

    @Override
    public Page<Order> getUserOrdersList(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getId(), pageable);
    }

    @Override
    @Transactional
    public Long postOrder(User user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getFurnitures().addAll(user.getFurnitureList());
        orderRepository.save(order);
        user.getFurnitureList().clear();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        return order.getId();
    }
}
