package com.pokemons.pokemons.service.use_case.auction;

import com.pokemons.pokemons.model.Auction;
import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.repository.DBAuctionRepository;
import com.pokemons.pokemons.requests.AuctionSellRequest;
import com.pokemons.pokemons.service.common.card_access.CardAccessService;
import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import com.pokemons.pokemons.view_model.AuctionBuyPageViewModel;
import com.pokemons.pokemons.view_model.AuctionViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        Auction auction = new Auction(card, trainer, request.getAmount(), request.getPrice());
        auctionRepository.save(auction);

        trainer.removeCard(card, request.getAmount());
        trainerAccessService.updateTrainer(trainer);
    }

    public AuctionBuyPageViewModel prepareAuctionBuyData(){
        List<Auction> auctions = auctionRepository.findAll();
        List<AuctionViewModel> auctionViewModels = new ArrayList<>();

        for (Auction auction : auctions) {
            AuctionViewModel auctionViewModel = AuctionViewModel.builder()
                    .auctionID(auction.getId())
                    .cardPrice(auction.getPrice())
                    .cardQuantity(auction.getQuantity())
                    .auctionOwner(auction.getTrainer().getName())
                    .cardURL(auction.getCard().getImageUrl())
                    .build();
            auctionViewModels.add(auctionViewModel);
        }

        AuctionBuyPageViewModel page = AuctionBuyPageViewModel.builder()
                .auctions(auctionViewModels)
                .userCash(trainerAccessService.getTrainerOfLoggedUser().getCash())
                .build();
        return page;
    }


}
