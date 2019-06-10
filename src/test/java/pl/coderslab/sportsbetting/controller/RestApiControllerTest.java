package pl.coderslab.sportsbetting.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestApiControllerTest {

    private RestApiController restApiController;

    @Mock
    GameService gameService;

    @Mock
    HorseServiceImpl horseService;

    @Mock
    ActionServiceImpl actionService;

    @Mock
    WalletServiceImpl walletService;

    @Mock
    CartServiceImpl cartService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        restApiController = new RestApiController();
        ReflectionTestUtils.setField(restApiController, "gameService", gameService);
        ReflectionTestUtils.setField(restApiController, "horseService", horseService);
        ReflectionTestUtils.setField(restApiController, "actionService", actionService);
        ReflectionTestUtils.setField(restApiController, "walletService", walletService);
        ReflectionTestUtils.setField(restApiController, "cartService", cartService);
    }

    @Test
    public void homeApi() {
        String result = restApiController.homeApi();
        assertEquals(result,"api");
    }

    @Test
    public void getLiveGamesAction() {
        when(gameService.findAllTodayGames()).thenReturn(new ArrayList<>());
        List<Game> result = restApiController.getLiveGamesAction();
        assertNotNull(result);
    }

    @Test
    public void getUpcoming() {
        when(gameService.findAllFutureGames()).thenReturn(new ArrayList<>());
        List<Game> result = restApiController.getUpcoming();
        assertNotNull(result);
    }

    @Test
    public void findGameById() {
        when(gameService.findEventById(1L)).thenReturn(new Game());
        Game result = restApiController.findGameById(1L);
        assertNotNull(result);
    }

    @Test
    public void findById() {
        when(horseService.findHorseById(1L)).thenReturn(new Horse());
        Horse result = restApiController.findById(1L);
        assertNotNull(result);
    }

    @Test
    public void findAction() {
    }

    @Test
    public void findCart() {
    }
}