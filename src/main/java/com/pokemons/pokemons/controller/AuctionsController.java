package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.requests.AuctionBuyRequest;
import com.pokemons.pokemons.requests.AuctionSellRequest;
import com.pokemons.pokemons.service.common.login.LoginService;
import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import com.pokemons.pokemons.service.use_case.auction.AuctionService;
import com.pokemons.pokemons.service.use_case.auction.AuctionServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auctions")
public class AuctionsController extends BaseController{
    private TrainerAccessService trainerAccessService;
    private AuctionService auctionService;

    public AuctionsController(TrainerAccessService trainerAccessService, AuctionService auctionService, LoginService loginService) {
        super(loginService);
        this.trainerAccessService = trainerAccessService;
        this.auctionService = auctionService;
    }

    @GetMapping("sell")
    public String getAuctionsSellForm(Model model){
        AuctionSellRequest request = new AuctionSellRequest();
        Map<Card, Integer> cards = trainerAccessService.getTrainerOfLoggedUser().getCards();
        model.addAttribute("cards", cards);
        model.addAttribute("request",request);
        //model.addAttribute(cards);
        return "auctions-sell";
    }

    @PostMapping("sell/{cardID}")
    public String sendAuctionsSellForm(Model model, @Valid AuctionSellRequest request, BindingResult br, @PathVariable String cardID ){

        if (br.hasErrors()){
            return redirectToIndex(model,br.getAllErrors().get(0).getDefaultMessage(),MessageType.ERROR);
        }

        request.setCardID(cardID);

        try {
            auctionService.createAuction(request);
        }
        catch (AuctionServiceException e){
            return redirectToIndex(model, e.getMessage(), MessageType.ERROR);
        }
        return  "redirect:/auctions/sell";
    }

    @GetMapping("buy")
    public String getAuctionsBuyForm(Model model){
        AuctionBuyRequest request = new AuctionBuyRequest();
        model.addAttribute("data",auctionService.prepareAuctionBuyData());
        model.addAttribute("request",request);
        return "auctions-buy";
    }

    @PostMapping("buy/{auctionID}")
    public String processAuctionBuyForm(Model model, @Valid AuctionBuyRequest request, BindingResult br, @PathVariable int auctionID ){

        request.setAuctionID(auctionID);

        try{
            auctionService.auctionBuy(request);
        }catch (AuctionServiceException e){
            return redirectToIndex(model, e.getMessage(), MessageType.ERROR);
        }

        return "redirect:/auctions/buy";
    }



}
