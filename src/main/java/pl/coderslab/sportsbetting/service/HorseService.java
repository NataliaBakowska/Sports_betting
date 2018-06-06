package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Horse;

public interface HorseService {

    void saveHorse(Horse horse);

    Horse findHorseById(Long id);
}
