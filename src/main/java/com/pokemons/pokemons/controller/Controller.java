package com.pokemons.pokemons.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping
    public String getHomePage(){
        return "Hello World";
    }
}
