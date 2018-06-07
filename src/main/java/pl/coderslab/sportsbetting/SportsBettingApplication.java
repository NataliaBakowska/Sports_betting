package pl.coderslab.sportsbetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.sportsbetting.service.FakerService;

@SpringBootApplication
@EnableScheduling
public class SportsBettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsBettingApplication.class, args);
    }
}


