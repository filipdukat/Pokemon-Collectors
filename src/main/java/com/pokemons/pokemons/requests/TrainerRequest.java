package com.pokemons.pokemons.requests;

import com.pokemons.pokemons.model.Sex;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TrainerRequest {
    private String name;
    private Sex sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "TrainerRequest{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
