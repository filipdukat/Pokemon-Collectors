package com.pokemons.pokemons.service;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBUserRepository;
import com.pokemons.pokemons.repository.UserRepository;
import com.pokemons.pokemons.requests.UserRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private DBUserRepository userRepository;

    public RegistrationService( @Qualifier("DB") DBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRequest userRequest){
        validateUser(userRequest);

        User user = new User(userRequest.getEmail(), userRequest.getPassword());

        userRepository.save(user);
    }

    private void validateUser(UserRequest userRequest){
        if (userRequest.getEmail().isEmpty() || !userRequest.getEmail().contains("@") || userRepository.existsById(userRequest.getEmail())){
            throw new RegistrationServiceException("Wrong email.");
        }

        if (userRequest.getPassword().length()<5){
            throw new RegistrationServiceException("Password should be at least 5 characters long.");
        }
    }

}
