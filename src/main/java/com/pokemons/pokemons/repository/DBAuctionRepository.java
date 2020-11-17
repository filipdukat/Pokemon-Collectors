package com.pokemons.pokemons.repository;

import com.pokemons.pokemons.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBAuctionRepository extends JpaRepository<Auction, Integer> {
}
