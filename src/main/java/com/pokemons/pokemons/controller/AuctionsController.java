package com.pokemons.pokemons.controller;

import com.pokemons.pokemons.model.Card;
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
        System.out.println(cards);
        return "auctions-sell";
    }

    @PostMapping("sell/{cardID}")
    public String sendAuctionsSellForm(@Valid AuctionSellRequest request, @PathVariable String cardID, BindingResult br, Model model){

        if (br.hasErrors()){
            return "error";
        }

        request.setCardID(cardID);

        try {
            auctionService.createAuction(request);
        }
        catch (AuctionServiceException e){
            return redirectToIndex(model, e.getMessage(), MessageType.ERROR);
            //chcemy zrobic klase wspolna dla wszystkich kontrolerow ktora bedzie dostarczac metode przekierowujaca do strony glownej (z komunikatem o bledzie)
        }
        return  "redirect:/auctions/sell";
    }

    @GetMapping("buy")
    public String getAuctionsBuyForm(){
        return "auctions-buy";
    }



}
