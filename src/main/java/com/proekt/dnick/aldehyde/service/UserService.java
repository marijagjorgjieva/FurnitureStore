package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.model.Order;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.dto.request.ChangePasswordRequest;
import com.proekt.dnick.aldehyde.dto.request.EditUserRequest;
import com.proekt.dnick.aldehyde.dto.request.SearchRequest;
import com.proekt.dnick.aldehyde.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getAuthenticatedUser();

    Page<Order> searchUserOrders(SearchRequest request, Pageable pageable);

    MessageResponse editUserInfo(EditUserRequest request);

    MessageResponse changePassword(ChangePasswordRequest request);
}
