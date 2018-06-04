package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;
    @Override
    public Game findEventById(Long id) {
        return gameRepository.findGameById(id);
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }


}
