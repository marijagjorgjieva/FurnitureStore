package com.proekt.dnick.aldehyde.controller;

import com.proekt.dnick.aldehyde.constants.Pages;
import com.proekt.dnick.aldehyde.constants.PathConstants;
import com.proekt.dnick.aldehyde.dto.request.UserRequest;
import com.proekt.dnick.aldehyde.dto.response.MessageResponse;
import com.proekt.dnick.aldehyde.service.RegistrationService;
import com.proekt.dnick.aldehyde.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.REGISTRATION)
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ControllerUtils controllerUtils;

    @GetMapping
    public String registration() {
        return Pages.REGISTRATION;
    }

    @PostMapping
    public String registration(@Valid UserRequest user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (controllerUtils.validateInputFields(bindingResult, model, "user", user)) {
            return Pages.REGISTRATION;
        }
        MessageResponse messageResponse = registrationService.registration( user);
        if (controllerUtils.validateInputField(model, messageResponse, "user", user)) {
            return Pages.REGISTRATION;
        }
        return controllerUtils.setAlertFlashMessage(redirectAttributes, PathConstants.LOGIN, messageResponse);
    }

}
