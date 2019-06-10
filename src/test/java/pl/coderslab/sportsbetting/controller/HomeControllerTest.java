package pl.coderslab.sportsbetting.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import pl.coderslab.sportsbetting.service.GameService;
import pl.coderslab.sportsbetting.service.GameServiceImpl;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private HomeController homeController;

    @Mock
    private Model model;

    @Mock
    private GameServiceImpl gameService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController();
        ReflectionTestUtils.setField(homeController, "gameService", gameService);
    }

    @Test
    public void index() {
        String result = homeController.index();
        assertEquals(result,"index");
    }

    @Test
    public void home() {
        String result = homeController.home(model);
        assertEquals(result,"home");
    }
}