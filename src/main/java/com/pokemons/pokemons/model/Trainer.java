package com.pokemons.pokemons.model;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
public class Trainer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Getter(AccessLevel.NONE)
    private Sex sex;
    @Getter(AccessLevel.NONE)
    private LocalDate birthDate;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Card, Integer> cards = new HashMap<>();
    private int cash = 100;

    public Trainer(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    Trainer(){

    }

    public int getCardsAmount(Card card){
        return cards.get(card);
    }

    public void addCard(Card card){
        if (cards.containsKey(card)){
            cards.put(card,cards.get(card)+1);
        }else{
            cards.put(card, 1);
        }
    }

    public void addCards(List<Card> newCards){
        for (Card newCard : newCards) {
            addCard(newCard);
        }
    }

    public void removeCard(Card card, int quantity){
            cards.put(card, cards.get(card)-quantity);

            if (cards.get(card) == 0){
                cards.remove(card);
            }
    }


    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                '}';
    }
}
