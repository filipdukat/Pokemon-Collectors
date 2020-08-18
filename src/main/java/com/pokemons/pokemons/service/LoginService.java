package com.pokemons.pokemons.service;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBUserRepository;
import com.pokemons.pokemons.requests.UserRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    private DBUserRepository userRepository;
    private List<User> loggedUsers = new ArrayList<>();

    public LoginService( @Qualifier("DB") DBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(UserRequest userRequest){
        User user = userRepository.findById(userRequest.getEmail()).get();
        if(user.getEmail().equals(userRequest.getEmail()) && user.getPassword().equals(userRequest.getPassword())){
            loggedUsers.add(user);
            System.out.println(loggedUsers);
        }else
            throw new LoginServiceException("Wrong username or password.");
    }
}
