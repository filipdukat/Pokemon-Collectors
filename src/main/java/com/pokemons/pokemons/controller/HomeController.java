package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController extends BaseController{
    public HomeController(LoginService loginService) {
        super(loginService);
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        System.err.println("ERROR");
        return redirectToIndex(model);
    }
}
