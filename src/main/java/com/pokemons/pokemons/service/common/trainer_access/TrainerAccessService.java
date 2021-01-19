package com.pokemons.pokemons.service.common.trainer_access;

import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBTrainerRepository;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerAccessService {
    private static final int DAILY_CASH = 10;

    private LoginService loginService;
    private DBTrainerRepository trainerRepository;

    public TrainerAccessService(LoginService loginService, DBTrainerRepository trainerRepository) {
        this.loginService = loginService;
        this.trainerRepository = trainerRepository;
    }

    public Trainer getTrainerOfLoggedUser(){
        User loggedUser = loginService.getLoggedUserOrThrow();

        return loggedUser.getTrainer();

    }

    public void updateTrainer(Trainer trainer){
        trainerRepository.save(trainer);
    }

    public void giveCash(){
        List<Trainer> trainers = trainerRepository.findAll();

        for (Trainer trainer : trainers) {
            trainer.addCash(DAILY_CASH);
        }

        trainerRepository.saveAll(trainers);
    }

}
