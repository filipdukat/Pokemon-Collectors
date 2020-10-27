package com.pokemons.pokemons.service.use_case.auction;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.requests.AuctionSellRequest;
import com.pokemons.pokemons.service.common.card_access.CardAccessService;
import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {
    private TrainerAccessService trainerAccessService;
    private CardAccessService cardAccessService;

    public AuctionService(TrainerAccessService trainerAccessService, CardAccessService cardAccessService) {
        this.trainerAccessService = trainerAccessService;
        this.cardAccessService = cardAccessService;
    }

    public void createAuction(AuctionSellRequest request){
        Card card = cardAccessService.getCardByID(request.getCardID()).orElseThrow(()->new AuctionServiceException("Can not find card."));

       if (trainerAccessService.getTrainerOfLoggedUser().getCardsAmount(card) < request.getAmount()){
           throw new AuctionServiceException("You do not have enough cards.");
       }
    }


}
