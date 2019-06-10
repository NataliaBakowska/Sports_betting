package pl.coderslab.sportsbetting.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.FakerService;
import pl.coderslab.sportsbetting.service.UserServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppStartTest {

    private AppStart appStart;

    @Mock
    FakerService fakerService;

    @Mock
    ActionServiceImpl actionService;

    @Mock
    WalletServiceImpl walletService;

    @Mock
    UserServiceImpl userService;

    @Mock
    private ApplicationArguments applicationArguments;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        appStart = new AppStart();
        ReflectionTestUtils.setField(appStart, "actionService", actionService);
        ReflectionTestUtils.setField(appStart, "fakerService", fakerService);
        ReflectionTestUtils.setField(appStart, "walletService", walletService);
        ReflectionTestUtils.setField(appStart, "userService", userService);
    }

    @Test
    public void run() throws Exception {
        appStart.run(applicationArguments);
    }

    @Test
    public void scanBets() {
        Game game = new Game();
        game.setFinishingAt(new Date());
        List<Action> actions = new ArrayList<>();
        Action action = new Action();
        Wallet wallet = new Wallet();
        wallet.setStatus(10.0);
        action.setWallet(wallet);
        action.setAmount(10.0);
        Horse horse = new Horse();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setHorse(horse);
        result.setPosition(1);
        result.setGame(game);
        results.add(result);
        horse.setResults(results);
        action.setHorse(horse);
        actions.add(action);
        when(actionService.findAllWhereBet(ActionType.BET)).thenReturn(actions);
        appStart.scanBets();
    }

    @Test
    public void scanBets2() {
        Game game = new Game();
        game.setFinishingAt(new Date());
        List<Action> actions = new ArrayList<>();
        Action action = new Action();
        Wallet wallet = new Wallet();
        wallet.setStatus(10.0);
        action.setWallet(wallet);
        action.setAmount(10.0);
        Horse horse = new Horse();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setHorse(horse);
        result.setPosition(2);
        result.setGame(game);
        results.add(result);
        horse.setResults(results);
        action.setHorse(horse);
        actions.add(action);
        when(actionService.findAllWhereBet(ActionType.BET)).thenReturn(actions);
        appStart.scanBets();
    }
}