package com.pokemons.pokemons.repository;


import com.pokemons.pokemons.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("DB")
public interface DBUserRepository extends JpaRepository<User, String> {

}
