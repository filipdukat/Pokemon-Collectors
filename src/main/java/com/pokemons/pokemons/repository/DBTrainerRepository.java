package com.pokemons.pokemons.repository;

import com.pokemons.pokemons.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBTrainerRepository extends JpaRepository<Trainer, Long> {

}
