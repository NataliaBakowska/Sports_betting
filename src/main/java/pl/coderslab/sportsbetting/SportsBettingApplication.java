package pl.coderslab.sportsbetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SportsBettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsBettingApplication.class, args);
    }
}


