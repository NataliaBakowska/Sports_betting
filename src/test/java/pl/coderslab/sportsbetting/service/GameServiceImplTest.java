package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.entity.Result;
import pl.coderslab.sportsbetting.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ResultServiceImpl resultService;

    GameServiceImpl gameService;

    @Before
    public void setUp() throws Exception {
        gameService = new GameServiceImpl(gameRepository, resultService);
    }

    @Test
    public void findEventById() {
        when(gameRepository.findGameById(1L)).thenReturn(new Game());
        Game result = gameService.findEventById(1L);
        assertNotNull(result);
    }

    @Test
    public void saveGame() {
        gameService.saveGame(new Game());
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    public void findAllFutureGames() {
        when(gameRepository.findGamesByStartingAtAfterToday()).thenReturn(new ArrayList<>());
        List<Game> result = gameService.findAllFutureGames();
        assertNotNull(result);
    }

    @Test
    public void findAllTodayGames() {
        when(gameRepository.findTodayGames()).thenReturn(new ArrayList<>());
        List<Game> result = gameService.findAllTodayGames();
        assertNotNull(result);
    }

    @Test
    public void findAllPastGames() {
        when(gameRepository.findPastGames()).thenReturn(new ArrayList<>());
        List<Game> result = gameService.findAllPastGames();
        assertNotNull(result);
    }

    @Test
    public void generateRandomLifeResults() {
        Result result = new Result();
        Horse horse1 = new Horse();
        horse1.setId(10L);
        result.setHorse(horse1);
        Result result2 = new Result();
        result2.setHorse(horse1);
        List<Result> results = new ArrayList<>();
        results.add(result);
        results.add(result2);
        List<Game> todayGames = new ArrayList<>();
        Game game1 = new Game();
        game1.setId(1L);
        game1.setResults(results);
        Game game2 = new Game();
        game2.setResults(results);
        game2.setId(2L);
        todayGames.add(game1);
        todayGames.add(game2);
        when(gameRepository.findTodayGames()).thenReturn(todayGames);
        when(resultService.findByGameIdAndHorseId(anyLong(),anyLong())).thenReturn(result);

        List<Game> exptected = gameService.generateRandomLifeResults();
        assertNotNull(exptected);
    }
}