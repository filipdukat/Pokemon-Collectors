package com.pokemons.pokemons.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Trainer {
    @Id
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private LocalDate birthDate;
    @ElementCollection(fetch = FetchType.EAGER)
//    @MapKeyColumn(name="key") // column name for map "key"
//    @Column(name="value") // column name for map "value"
    private Map<Card, Integer> cards = new HashMap<>();

    public Trainer(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    private Trainer(){

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

    public Map<Card, Integer> getCards() {
        return cards;
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
