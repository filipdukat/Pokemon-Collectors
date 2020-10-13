package com.pokemons.pokemons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auctions")
public class AuctionsController {
    @GetMapping("sell")
    public String getAuctionsSellForm(){
        return "auctions-sell";
    }

    @GetMapping("buy")
    public String getAuctionsBuyForm(){
        return "auctions-buy";
    }


}
