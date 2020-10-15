package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/auctions")
public class AuctionsController{
    private TrainerAccessService trainerAccessService;

    public AuctionsController(TrainerAccessService trainerAccessService) {
        this.trainerAccessService = trainerAccessService;
    }

    @GetMapping("sell")
    public String getAuctionsSellForm(Model model){
        Map<Card, Integer> cards = trainerAccessService.getTrainerOfLoggedUser().getCards();
        model.addAttribute("cards", cards);
        //model.addAttribute(cards);
        System.out.println(cards);
        return "auctions-sell";
    }

    @GetMapping("buy")
    public String getAuctionsBuyForm(){
        return "auctions-buy";
    }



}
