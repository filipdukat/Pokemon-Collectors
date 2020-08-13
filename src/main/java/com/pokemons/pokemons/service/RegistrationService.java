package com.pokemons.pokemons.service;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.UserRepository;
import com.pokemons.pokemons.requests.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRequest userRequest){
        validateUser(userRequest);

        User user = new User(userRequest.getEmail(), userRequest.getEmail());

        userRepository.save(user);
    }

    private void validateUser(UserRequest userRequest){
        if (userRequest.getEmail().isEmpty() || !userRequest.getEmail().contains("@")){
            throw new RegistrationServiceException("Wrong email.");
        }

        if (userRequest.getPassword().length()<5){
            throw new RegistrationServiceException("Password should be at least 5 characters long.");
        }
    }

}
