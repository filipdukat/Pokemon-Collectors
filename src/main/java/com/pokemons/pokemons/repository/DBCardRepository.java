package com.pokemons.pokemons.repository;

import com.pokemons.pokemons.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBCardRepository extends JpaRepository<Card, String> {

}
