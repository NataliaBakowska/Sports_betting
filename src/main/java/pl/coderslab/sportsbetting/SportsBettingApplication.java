package pl.coderslab.sportsbetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.coderslab.sportsbetting.service.FakerService;

@SpringBootApplication
public class SportsBettingApplication {

    public static void main(String[] args) {
        FakerService fakerService = new FakerService();
//        fakerService.generateGame();
        SpringApplication.run(SportsBettingApplication.class, args);
    }
}
