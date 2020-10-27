package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.service.common.login.LoginService;
import com.pokemons.pokemons.service.use_case.packs_opening.PacksOpeningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PacksOpeningController extends BaseController{
    private PacksOpeningService packsOpeningService;

    public PacksOpeningController(PacksOpeningService packsOpeningService, LoginService loginService) {
        super(loginService);
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
