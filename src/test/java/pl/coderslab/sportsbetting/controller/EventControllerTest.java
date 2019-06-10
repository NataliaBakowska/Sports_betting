package pl.coderslab.sportsbetting.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import pl.coderslab.sportsbetting.entity.CurrentUser;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.entity.Result;
import pl.coderslab.sportsbetting.service.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

    private EventController eventController;

    @Mock
    private CurrentUser currentUser;

    @Mock
    private Model model;

    @Mock
    private HorseServiceImpl horseService;

    @Mock
    private GameServiceImpl gameService;

    @Mock
    private FakerService fakerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        eventController = new EventController();
        ReflectionTestUtils.setField(eventController,"horseService", horseService);
        ReflectionTestUtils.setField(eventController,"gameService", gameService);
        ReflectionTestUtils.setField(eventController,"fakerService", fakerService);
    }

    @Test
    public void showUpcomingEvents() {
        String result = eventController.showUpcomingEvents(model);
        assertEquals(result, "events");
    }

    @Test
    public void showEventDetails() {
        String result = eventController.showUpcomingEvents(model);
        assertEquals(result, "events");
    }

    @Test
    public void showCurrentEventDetails() {
        Game game = new Game();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setHorse(new Horse());
        results.add(result);
        game.setResults(results);
        when(gameService.findEventById(1L)).thenReturn(game);
        String expected = eventController.showCurrentEventDetails(1L, model);
        assertEquals(expected, "eventDetailsCurrent");
    }

    @Test
    public void showPastEvents() {
        String result = eventController.showPastEvents(model);
        assertEquals(result, "pastEvents");
    }

    @Test
    public void endGame() {
        when(gameService.findEventById(1L)).thenReturn(new Game());
        String result = eventController.endGame(1L, model);
        assertEquals(result, "redirect:/home");
    }

    @Test
    public void generateUpcoming() {
        String result = eventController.generateUpcoming(model);
        assertEquals(result, "redirect:/home");
    }
}