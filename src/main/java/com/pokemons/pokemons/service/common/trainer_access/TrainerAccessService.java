package com.pokemons.pokemons.service.common.trainer_access;

import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBTrainerRepository;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Service;

@Service
public class TrainerAccessService {
    private LoginService loginService;
    private DBTrainerRepository trainerRepository;

    public TrainerAccessService(LoginService loginService, DBTrainerRepository trainerRepository) {
        this.loginService = loginService;
        this.trainerRepository = trainerRepository;
    }


    public Trainer getTrainerOfLoggedUser(){
        User loggedUser = loginService.getLoggedUserOrThrow();

        System.out.println(loggedUser);

        return loggedUser.getTrainer();

    }

    public void updateTrainer(Trainer trainer){
        trainerRepository.save(trainer);
    }

}
