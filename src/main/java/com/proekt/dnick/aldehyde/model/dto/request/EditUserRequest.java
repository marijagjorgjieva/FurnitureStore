package com.proekt.dnick.aldehyde.model.dto.request;

import com.proekt.dnick.aldehyde.configs.constants.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditUserRequest {

    @NotBlank(message = ErrorMessage.EMPTY_FIRST_NAME)
    private String firstName;

    @NotBlank(message = ErrorMessage.EMPTY_LAST_NAME)
    private String lastName;

    private String city;
    private String address;
    private String phoneNumber;
    private String email;
}
