package com.pokemons.pokemons.service.packs_opening;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.repository.DBCardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PacksOpeningService {
    private DBCardRepository dbCardRepository;

    public PacksOpeningService(DBCardRepository dbCardRepository) {
        this.dbCardRepository = dbCardRepository;
    }

    public List<Card> getCards(){
        List<Card> cards = dbCardRepository.findAll();
        List<Card> randomCards = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Card randomCard = cards.get(random.nextInt(cards.size()));
            randomCards.add(randomCard);
        }

        return randomCards;
    }
}
