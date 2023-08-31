package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.dto.request.UserRequest;

public interface RegistrationService {

    MessageResponse registration( UserRequest user);

}
