package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.requests.UserRequest;
import com.pokemons.pokemons.service.common.login.LoginService;
import com.pokemons.pokemons.service.common.login.LoginServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController extends BaseController{
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        super(loginService);
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        UserRequest userRequest = new UserRequest();
        model.addAttribute("userRequest", userRequest);
        return "login-form";
    }

}
