package pl.coderslab.sportsbetting.configuration;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.FakerService;
import pl.coderslab.sportsbetting.service.UserServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;
import java.util.List;


@Component
public class AppStart implements ApplicationRunner {

    @Autowired
    FakerService fakerService;

    @Autowired
    ActionServiceImpl actionService;

    @Autowired
    WalletServiceImpl walletService;

    @Autowired
    UserServiceImpl userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i =0;i<=15;i++) {
            fakerService.generateGame();
        }
        fakerService.generateCurrentGame();
        fakerService.generateCurrentGame();
        fakerService.generateUser();
        fakerService.generateGameToBegin();
    }


    @Scheduled(cron="* */2 * * * *")
    public void scanBets() {
        List<Action> actions = actionService.findAllWhereBet("Bet");
        for (Action a : actions) {
            Wallet wallet = a.getWallet();
            Horse horse = a.getHorse();
            List<Result> results = horse.getResults();
            for (Result r : results) {
                Game game = r.getGame();
                if (LocalDateTime.fromDateFields(game.getFinishingAt()).isBefore(LocalDateTime.now())) {
                    Action action = new Action();
                    if (r.getPosition() == 1 && a.getStatus() == null) {
                        wallet.setStatus(wallet.getStatus() + a.getAmount() * 100);
                        a.setStatus("finished");
                        actionService.saveAction(a);
                        action.setName("Success!");
                        action.setAmount(a.getAmount() * 100);
                        action.setWallet(wallet);
                        action.setHorse(horse);
                        action.setCreated(LocalDateTime.now());
                        actionService.saveAction(action);
                        walletService.updateWallet(wallet);
                    } else if (r.getPosition() != 1 && a.getStatus() == null){
                        a.setStatus("finished");
                        actionService.saveAction(a);
                        action.setName("You've lost!");
                        action.setHorse(horse);
                        action.setCreated(LocalDateTime.now());
                        action.setWallet(wallet);
                        actionService.saveAction(action);
                    }
                }
            }
        }
    }
}
