package com.pokemons.pokemons.service.use_case.packs_opening;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.repository.DBCardRepository;
import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PacksOpeningService {
    private DBCardRepository dbCardRepository;
    private TrainerAccessService trainerAccessService;

    public PacksOpeningService(DBCardRepository dbCardRepository, TrainerAccessService trainerAccessService) {
        this.dbCardRepository = dbCardRepository;
        this.trainerAccessService = trainerAccessService;
    }

    public List<Card> getCards(){
        List<Card> cards = dbCardRepository.findAll();
        List<Card> randomCards = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Card randomCard = cards.get(random.nextInt(cards.size()));
            randomCards.add(randomCard);
        }

        Trainer trainer = trainerAccessService.getTrainerOfLoggedUser();

        trainer.addCards(randomCards);

        trainerAccessService.updateTrainer(trainer);

        return randomCards;
    }
}
