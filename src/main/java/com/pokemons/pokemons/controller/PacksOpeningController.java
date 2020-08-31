package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.service.packs_opening.PacksOpeningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PacksOpeningController {
    private PacksOpeningService packsOpeningService;

    public PacksOpeningController(PacksOpeningService packsOpeningService) {
        this.packsOpeningService = packsOpeningService;
    }

    @GetMapping("/packs-opening")
    public String getPacksOpeningForm(){
        return "packs-opening";
    }

    @GetMapping("/new-cards")
    public String openPack(Model model){
        model.addAttribute("cards",packsOpeningService.getCards());

        /*try {
            loginService.login(userRequest);
        }
        catch (LoginServiceException e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }*/

        return "new-cards";
    }
}
