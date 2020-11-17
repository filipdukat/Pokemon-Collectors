package com.pokemons.pokemons.service.use_case.auction;

import com.pokemons.pokemons.model.Auction;
import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.repository.DBAuctionRepository;
import com.pokemons.pokemons.requests.AuctionSellRequest;
import com.pokemons.pokemons.service.common.card_access.CardAccessService;
import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {
    private TrainerAccessService trainerAccessService;
    private CardAccessService cardAccessService;
    private DBAuctionRepository auctionRepository;

    public AuctionService(TrainerAccessService trainerAccessService, CardAccessService cardAccessService, DBAuctionRepository auctionRepository) {
        this.trainerAccessService = trainerAccessService;
        this.cardAccessService = cardAccessService;
        this.auctionRepository = auctionRepository;
    }

    public void createAuction(AuctionSellRequest request){
        Card card = cardAccessService.getCardByID(request.getCardID()).orElseThrow(()->new AuctionServiceException("Can not find card."));

        Trainer trainer = trainerAccessService.getTrainerOfLoggedUser();

        if (trainer.getCardsAmount(card) < request.getAmount()){
           throw new AuctionServiceException("You do not have enough cards.");
       }

        Auction auction = new Auction(card, request.getAmount(), request.getPrice());
        auctionRepository.save(auction);

        trainer.removeCard(card, request.getAmount());
        trainerAccessService.updateTrainer(trainer);
    }


}
