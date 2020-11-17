package com.pokemons.pokemons.client;

import com.pokemons.pokemons.model.Card;
import com.pokemons.pokemons.model.Cards;
import com.pokemons.pokemons.repository.DBCardRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TCGApiClient {
    private static final String URL = "https://api.pokemontcg.io/v1/";
    private RestTemplate restTemplate;
    private DBCardRepository dbCardRepository;

    public TCGApiClient(RestTemplate restTemplate, DBCardRepository dbCardRepository) {
        this.restTemplate = restTemplate;
        this.dbCardRepository = dbCardRepository;
    }

    @PostConstruct
    public void downloadAllCards(){
        Cards cards = restTemplate.getForObject(URL + "cards", Cards.class);
        List<Card> cardsList = cards.getCards();
        dbCardRepository.saveAll(cardsList);


    }
}
