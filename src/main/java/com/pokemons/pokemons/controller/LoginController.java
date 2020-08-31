package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.requests.UserRequest;
import com.pokemons.pokemons.service.login.LoginService;
import com.pokemons.pokemons.service.login.LoginServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        UserRequest userRequest = new UserRequest();
        model.addAttribute("userRequest", userRequest);
        return "login-form";
    }

    @PostMapping("/login")
    public String sendLoginData(UserRequest userRequest, Model model){
        System.out.println(userRequest);

        try {
            loginService.login(userRequest);
        }
        catch (LoginServiceException e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "index";
    }
}
