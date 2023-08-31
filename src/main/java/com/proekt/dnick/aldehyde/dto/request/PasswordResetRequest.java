package com.proekt.dnick.aldehyde.dto.request;

import com.proekt.dnick.aldehyde.constants.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordResetRequest {

    private String email;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD2_CHARACTER_LENGTH)
    private String password2;
}
