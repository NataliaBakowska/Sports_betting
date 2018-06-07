package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Game;

import java.util.List;

public interface GameService {

    Game findEventById(Long id);

    void saveGame(Game game);

    List<Game> findAllFutureGames();

    List<Game> findAllTodayGames();

    List<Game> findAllPastGames();

}
