package com.pokemons.pokemons.service.common.login;

import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBUserRepository;
import com.pokemons.pokemons.requests.UserRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {
    private DBUserRepository userRepository;

    public LoginService(@Qualifier("DB") DBUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedUserOrThrow() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new LoginServiceException("User not logged.");
        }
    }

    public Optional<User> getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User){
            return Optional.of((User) principal);
        }else{
            return Optional.empty();
        }
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(()->new UsernameNotFoundException("User not found."));
    }
}
