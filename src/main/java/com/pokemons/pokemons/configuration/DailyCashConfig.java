package com.pokemons.pokemons.configuration;

import com.pokemons.pokemons.service.common.trainer_access.TrainerAccessService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class DailyCashConfig {
    private TrainerAccessService trainerAccessService;

    public DailyCashConfig(TrainerAccessService trainerAccessService) {
        this.trainerAccessService = trainerAccessService;
    }

    //@Scheduled(cron = "00 03 16 * * *")
    @Scheduled(cron = "00 00 10 * * *")
    public void giveCashEveryDay() {
        trainerAccessService.giveCash();
    }
}
