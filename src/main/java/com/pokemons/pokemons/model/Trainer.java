package com.pokemons.pokemons.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Trainer {
    @Id
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private LocalDate birthDate;

    public Trainer(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    private Trainer(){

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
