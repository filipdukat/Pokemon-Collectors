package com.pokemons.pokemons.service.trainer_creation;

import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.repository.DBTrainerRepository;
import com.pokemons.pokemons.requests.TrainerRequest;
import org.springframework.stereotype.Service;

@Service
public class TrainerCreationService {
    private DBTrainerRepository trainerRepository;

    public TrainerCreationService(DBTrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void create(TrainerRequest trainerRequest){
        validateTrainer(trainerRequest);

        Trainer trainer = new Trainer(trainerRequest.getName(), trainerRequest.getSex(), trainerRequest.getBirthDate());

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
