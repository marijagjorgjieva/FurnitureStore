package com.proekt.dnick.aldehyde.dto.response;

import com.proekt.dnick.aldehyde.model.Order;
import com.proekt.dnick.aldehyde.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private User user;
    private Page<Order> orders;
}
