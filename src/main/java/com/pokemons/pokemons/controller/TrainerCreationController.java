package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.requests.TrainerRequest;
import com.pokemons.pokemons.service.common.login.LoginService;
import com.pokemons.pokemons.service.use_case.trainer_creation.TrainerCreationException;
import com.pokemons.pokemons.service.use_case.trainer_creation.TrainerCreationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrainerCreationController extends BaseController{
    private TrainerCreationService trainerCreationService;

    public TrainerCreationController(TrainerCreationService trainerCreationService, LoginService loginService) {
        super(loginService);
        this.trainerCreationService = trainerCreationService;
    }

    @GetMapping("/trainer-creation")
    public String getTrainerCreationForm(Model model){
        TrainerRequest trainerRequest = new TrainerRequest();
        model.addAttribute("trainerRequest", trainerRequest);
        return "trainer-creation-form";
    }

    @PostMapping("/trainer-creation")
    public String sendTrainerCreationData(TrainerRequest trainerRequest, Model model){

        try {
            trainerCreationService.create(trainerRequest);
        }
        catch (TrainerCreationException e){
            return redirectToIndex(model, e.getMessage(), MessageType.ERROR);
        }

        return "redirect:/";
    }
}
