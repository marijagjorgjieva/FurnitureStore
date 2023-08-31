package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.model.Order;
import com.proekt.dnick.aldehyde.model.Furniture;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.model.dto.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Furniture> getOrdering();

    Page<Order> getUserOrdersList(Pageable pageable);

    Long postOrder(User user, OrderRequest orderRequest);
}
