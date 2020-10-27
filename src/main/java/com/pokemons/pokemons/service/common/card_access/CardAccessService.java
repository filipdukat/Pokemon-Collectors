package com.pokemons.pokemons.service.common.card_access;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.repository.DBCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardAccessService {
    private DBCardRepository dbCardRepository;

    public CardAccessService(DBCardRepository dbCardRepository) {
        this.dbCardRepository = dbCardRepository;
    }

    public Optional<Card> getCardByID(String cardID){
        return dbCardRepository.findById(cardID);
    }
}
