package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.dto.request.PasswordResetRequest;
import com.proekt.dnick.aldehyde.dto.response.MessageResponse;

public interface AuthenticationService {

    MessageResponse sendPasswordResetCode(String email);

    String getEmailByPasswordResetCode(String code);

    MessageResponse resetPassword(PasswordResetRequest request);
}
