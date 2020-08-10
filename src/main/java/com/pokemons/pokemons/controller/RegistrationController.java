package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String getHomePage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping("/register")
    public String sendRegistrationData(User user){
        System.out.println(user);
        return "index";
    }
}
