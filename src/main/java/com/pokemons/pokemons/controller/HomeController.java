package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {
    private LoginService loginService;

    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        Optional<User> userOptional = loginService.getLoggedUser();

        userOptional.ifPresent(user -> model.addAttribute("user", user));

        model.addAttribute("hasTrainer", false);
        userOptional.ifPresent(user -> model.addAttribute("hasTrainer", user.getTrainer() != null));

        return "index";
    }

}
