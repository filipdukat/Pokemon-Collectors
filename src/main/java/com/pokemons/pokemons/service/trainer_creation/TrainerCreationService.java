package com.pokemons.pokemons.service.trainer_creation;

import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.repository.DBTrainerRepository;
import com.pokemons.pokemons.requests.TrainerRequest;
import com.pokemons.pokemons.service.login.LoginService;
import com.pokemons.pokemons.service.registration.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class TrainerCreationService {
    private DBTrainerRepository trainerRepository;
    private LoginService loginService;

    public TrainerCreationService(DBTrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void create(TrainerRequest trainerRequest){
        validateTrainer(trainerRequest);

        Trainer trainer = new Trainer(trainerRequest.getName(), trainerRequest.getSex(), trainerRequest.getBirthDate());

        loginService.getLoggedUser();

        trainerRepository.save(trainer);
    }

    private void validateTrainer(TrainerRequest trainerRequest){
        if (trainerRequest.getName().equals("")){
            throw new TrainerCreationException("Name can not be empty.");
        }else if(trainerRequest.getSex() == null){

        }else if (trainerRequest.getBirthDate() == null){

        }
    }
}
