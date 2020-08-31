package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.requests.TrainerRequest;
import com.pokemons.pokemons.service.trainer_creation.TrainerCreationException;
import com.pokemons.pokemons.service.trainer_creation.TrainerCreationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrainerCreationController {
    private TrainerCreationService trainerCreationService;

    public TrainerCreationController(TrainerCreationService trainerCreationService) {
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
        System.out.println(trainerRequest);

        try {
            trainerCreationService.create(trainerRequest);
        }
        catch (TrainerCreationException e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "index";
    }
}
