package com.proekt.dnick.aldehyde.service;

import com.proekt.dnick.aldehyde.model.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.model.dto.request.UserRequest;

public interface RegistrationService {

    MessageResponse registration( UserRequest user);

}
