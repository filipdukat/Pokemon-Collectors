package com.pokemons.pokemons.service.use_case.registration;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBUserRepository;
import com.pokemons.pokemons.requests.UserRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RegistrationService {
    private DBUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationService(@Qualifier("DB") DBUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void prepareTestUser(){
        if (userRepository.count()== 0){
            UserRequest user = new UserRequest("email@email.com", "123456");
            register(user);
        }
    }

    public void register(UserRequest userRequest){
        validateUser(userRequest);
        User user = new User(userRequest.getEmail(), passwordEncoder.encode(userRequest.getPassword()));
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
