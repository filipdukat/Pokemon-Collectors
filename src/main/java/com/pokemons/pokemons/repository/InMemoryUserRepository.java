package com.pokemons.pokemons.repository;

import com.pokemons.pokemons.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        System.out.println(users);
        return user;
    }

    @Override
    public String toString() {
        return "InMemoryUserRepository{" +
                "users=" + users +
                '}';
    }
}
