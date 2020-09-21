package com.pokemons.pokemons.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {
    @Id
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private LocalDate birthDate;
    @ManyToMany
    private List<Card> cards = new ArrayList<>();

    public Trainer(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    private Trainer(){

    }

    public void addCards(List<Card> newCards){
        cards.addAll(newCards);
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
