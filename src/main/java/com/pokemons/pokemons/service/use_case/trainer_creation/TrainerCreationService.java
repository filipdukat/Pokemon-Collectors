package com.pokemons.pokemons.service.use_case.trainer_creation;

import com.pokemons.pokemons.model.Trainer;
import com.pokemons.pokemons.model.User;
import com.pokemons.pokemons.repository.DBTrainerRepository;
import com.pokemons.pokemons.requests.TrainerRequest;
import com.pokemons.pokemons.service.common.login.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TrainerCreationService {
    private DBTrainerRepository trainerRepository;
    private LoginService loginService;

    public TrainerCreationService(DBTrainerRepository trainerRepository, LoginService loginService) {
        this.trainerRepository = trainerRepository;
        this.loginService = loginService;
    }

    @PostConstruct
    public void attachTrainerForAdmin(){
        attachTrainer(trainerRepository.getOne(1L), loginService.getAdmin());
        //todo
        //wciganac trenera i usera i sprawdzic czy dziala
    }

    public void create(TrainerRequest trainerRequest){
        User loggedUser = loginService.getLoggedUserOrThrow();
        createForUser(trainerRequest, loggedUser);
    }

    private void createForUser(TrainerRequest trainerRequest, User user){
        validateTrainer(trainerRequest);

        Trainer trainer = new Trainer(trainerRequest.getName(), trainerRequest.getSex(), trainerRequest.getBirthDate());

        attachTrainer(trainer,user);
    }

    private void attachTrainer(Trainer trainer, User user){
        user.setTrainer(trainer);

        trainerRepository.save(trainer);

        loginService.updateUser(user);
    }


    private void validateTrainer(TrainerRequest trainerRequest){
        if (trainerRequest.getName().equals("")){
            throw new TrainerCreationException("Name can not be empty.");
        }else if(trainerRequest.getSex() == null){
        //todo
        }else if (trainerRequest.getBirthDate() == null){

        }
    }
}
