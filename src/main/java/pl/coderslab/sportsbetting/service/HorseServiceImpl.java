package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.repository.HorseRepository;

@Service
public class HorseServiceImpl implements HorseService {

    @Autowired
    HorseRepository horseRepository;

    @Override
    public void saveHorse(Horse horse) {
        horseRepository.save(horse);
    }
}
