package pl.coderslab.sportsbetting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.sportsbetting.service.FakerService;

import java.util.Random;

@Component
public class AppStart implements ApplicationRunner {

    @Autowired
    FakerService fakerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i =0;i<=15;i++) {
            fakerService.generateGame();
        }
        fakerService.generateCurrentGame();
        fakerService.generateCurrentGame();

    }
}
