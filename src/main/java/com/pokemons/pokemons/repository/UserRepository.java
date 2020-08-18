package com.pokemons.pokemons.repository;

import com.pokemons.pokemons.model.User;

public interface UserRepository {
    User save(User user);
}
