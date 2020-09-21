package com.pokemons.pokemons.service.use_case.trainer_creation;

import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBTrainerRepository;
import com.pokemons.pokemons.requests.TrainerRequest;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Service;

@Service
public class TrainerCreationService {
    private DBTrainerRepository trainerRepository;
    private LoginService loginService;

    public TrainerCreationService(DBTrainerRepository trainerRepository, LoginService loginService) {
        this.trainerRepository = trainerRepository;
        this.loginService = loginService;
    }

    public void create(TrainerRequest trainerRequest){
        validateTrainer(trainerRequest);

        Trainer trainer = new Trainer(trainerRequest.getName(), trainerRequest.getSex(), trainerRequest.getBirthDate());

        User loggedUser = loginService.getLoggedUserOrThrow();

        loggedUser.setTrainer(trainer);

        trainerRepository.save(trainer);

        loginService.updateUser(loggedUser);
    }

    private void validateTrainer(TrainerRequest trainerRequest){
        if (trainerRequest.getName().equals("")){
            throw new TrainerCreationException("Name can not be empty.");
        }else if(trainerRequest.getSex() == null){

        }else if (trainerRequest.getBirthDate() == null){

        }
    }
}
