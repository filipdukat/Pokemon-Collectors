package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private LoginService loginService;

    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        loginService.getLoggedUser().ifPresent(user -> model.addAttribute("user", user));
        return "index";
    }
}
