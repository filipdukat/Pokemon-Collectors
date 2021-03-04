package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class BaseController {
    private LoginService loginService;

    public BaseController(LoginService loginService) {
        this.loginService = loginService;
    }

    public String redirectToIndex(Model model, String message, MessageType messageType){
        model.addAttribute(messageType.getVariableName(),message);

        Optional<User> userOptional = loginService.getLoggedUser();

        userOptional.ifPresent(user -> model.addAttribute("user", user));

        model.addAttribute("hasTrainer", false);

        userOptional.ifPresent(user -> model.addAttribute("hasTrainer", user.getTrainer() != null));

        System.err.println(model);

        return "index";
    }

    public String redirectToIndex(Model model){
       return redirectToIndex(model, "", MessageType.NONE);
    }

}

enum MessageType{
    SUCCESS("success"), ERROR("error"), NONE("");
    private String variableName;

    MessageType(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }
}
