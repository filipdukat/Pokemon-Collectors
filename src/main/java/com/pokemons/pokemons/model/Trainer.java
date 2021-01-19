package com.pokemons.pokemons.model;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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

    public void addCard(Card card, int quantity){
        if (cards.containsKey(card)){
            cards.put(card,cards.get(card)+quantity);
        }else{
            cards.put(card, quantity);
        }
    }

    public void addCards(List<Card> newCards){
        for (Card newCard : newCards) {
            addCard(newCard, 1);
        }
    }

    public void removeCard(Card card, int quantity){
            cards.put(card, cards.get(card)-quantity);

            if (cards.get(card) == 0){
                cards.remove(card);
            }
    }

    public void addCash(int addedCash){
        cash += addedCash;
    }

    public void removeCash(int removedCash){
        cash -= removedCash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
