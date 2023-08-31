package com.proekt.dnick.aldehyde.service.impl;

import com.proekt.dnick.aldehyde.configs.constants.ErrorMessage;
import com.proekt.dnick.aldehyde.configs.constants.SuccessMessage;
import com.proekt.dnick.aldehyde.model.Role;
import com.proekt.dnick.aldehyde.model.User;
import com.proekt.dnick.aldehyde.model.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.model.dto.request.UserRequest;
import com.proekt.dnick.aldehyde.repository.UserRepository;
import com.proekt.dnick.aldehyde.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;



    @Override
    @Transactional
    public MessageResponse registration( UserRequest userRequest) {
        if (userRequest.getPassword() != null && !userRequest.getPassword().equals(userRequest.getPassword2())) {
            return new MessageResponse("passwordError", ErrorMessage.PASSWORDS_DO_NOT_MATCH);
        }
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            return new MessageResponse("emailError", ErrorMessage.EMAIL_IN_USE);
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", user.getFirstName());
        return new MessageResponse("alert-success", SuccessMessage.USER_ACTIVATED);
    }


}
