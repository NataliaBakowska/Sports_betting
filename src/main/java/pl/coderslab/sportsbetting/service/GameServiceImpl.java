package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.entity.Result;
import pl.coderslab.sportsbetting.comparator.ResultComparator;
import pl.coderslab.sportsbetting.repository.GameRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ResultServiceImpl resultService;

    @Override
    public Game findEventById(Long id) {
        return gameRepository.findGameById(id);
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public List<Game> findAllFutureGames() {
        return gameRepository.findGamesByStartingAtAfterToday();
    }

    @Override
    public List<Game> findAllTodayGames() {
        return gameRepository.findTodayGames();
    }

    @Override
    public List<Game> findAllPastGames() {
        return gameRepository.findPastGames();
    }

//    @Scheduled(cron="* */2 * * * *")
    public List<Game> generateRandomLifeResults() {
        List<Game> games = this.findAllTodayGames();
        Random rand = new Random();
        for(Game g : games){
            List<Result> results = g.getResults();
            Integer[] list = new Integer[results.size()];
            for(int i =0 ; i<list.length;i++){
                list[i]=i+1;
            }
            Collections.shuffle(Arrays.asList(list));
            for(int i = 0; i<list.length;i++){
                Horse horse = results.get(i).getHorse();
                Result result = resultService.findByGameIdAndHorseId(g.getId(),horse.getId());
                result.setPosition(list[i]);
                resultService.saveResult(result);
            }
            Collections.sort(results,new ResultComparator());
        }
        return games;
    }


}
