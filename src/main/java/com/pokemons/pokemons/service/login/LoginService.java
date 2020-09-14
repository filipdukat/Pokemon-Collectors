package com.pokemons.pokemons.service.login;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBUserRepository;
import com.pokemons.pokemons.requests.UserRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {
    private DBUserRepository userRepository;
    private User loggedUser;

    public LoginService(@Qualifier("DB") DBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(UserRequest userRequest){
        User user = userRepository.findById(userRequest.getEmail()).get();
        if(user.getEmail().equals(userRequest.getEmail()) && user.getPassword().equals(userRequest.getPassword())){
           loggedUser = user;
        }else
            throw new LoginServiceException("Wrong username or password.");
    }

    public Optional<User> getLoggedUser() {
        return Optional.ofNullable(loggedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findById(username).orElseThrow(()->new UsernameNotFoundException("User not found."));
    }
}
