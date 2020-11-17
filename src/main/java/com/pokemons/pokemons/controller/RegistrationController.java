package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.requests.UserRequest;
import com.pokemons.pokemons.service.common.login.LoginService;
import com.pokemons.pokemons.service.use_case.registration.RegistrationService;
import com.pokemons.pokemons.service.use_case.registration.RegistrationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController extends BaseController{
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService, LoginService loginService) {
        super(loginService);
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserRequest userRequest = new UserRequest();
        model.addAttribute("userRequest", userRequest);
        return "registration-form";
    }

    @PostMapping("/register")
    public String sendRegistrationData(UserRequest userRequest, Model model){

        try {
            registrationService.register(userRequest);
        }
        catch (RegistrationServiceException e){
            return redirectToIndex(model,e.getMessage(),MessageType.ERROR);
        }
        return "index";
    }
}
