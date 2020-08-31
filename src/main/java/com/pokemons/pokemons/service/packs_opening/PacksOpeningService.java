package com.pokemons.pokemons.service.packs_opening;

import com.pokemons.pokemons.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PacksOpeningService {
    private List<Card> cards;

    public PacksOpeningService() {
        cards = List.of(new Card("Card 1"), new Card("Card 2"), new Card("Card 3"), new Card("Card 4"), new Card("Card 5"));
    }

    public List<Card> getCards(){
        Random random = new Random();
        int rand1 = random.nextInt(5);
        int rand2 = random.nextInt(5);

        List<Card> randomCards;

        return randomCards = List.of(cards.get(rand1), cards.get(rand2));
    }
}
