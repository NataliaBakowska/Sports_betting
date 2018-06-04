package pl.coderslab.sportsbetting.controller;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.CurrentUser;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.WalletRepository;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;

@Controller
public class WalletController {

    @Autowired
    WalletServiceImpl walletService;

    @Autowired
    ActionServiceImpl actionService;

    @GetMapping("/recharge/10")
    String recharge10(@AuthenticationPrincipal CurrentUser customUser){
        Wallet wallet = walletService.findByUserId(customUser.getUser().getId());
        wallet.setStatus(wallet.getStatus() + 10);
        walletService.updateWallet(wallet);
        createActionRecharged(wallet,10.00);
        return"redirect:/userDetails";
    }



    @GetMapping("/recharge/20")
    String recharge20(@AuthenticationPrincipal CurrentUser customUser){
        Wallet wallet = walletService.findByUserId(customUser.getUser().getId());
        wallet.setStatus(wallet.getStatus() + 20);
        walletService.updateWallet(wallet);
        createActionRecharged(wallet,20.00);
        return"redirect:/userDetails";
    }

    @GetMapping("/recharge/50")
    String recharge50(@AuthenticationPrincipal CurrentUser customUser){
        Wallet wallet = walletService.findByUserId(customUser.getUser().getId());
        wallet.setStatus(wallet.getStatus() + 50);
        walletService.updateWallet(wallet);
        createActionRecharged(wallet,50.00);
        return"redirect:/userDetails";
    }

    @GetMapping("/recharge/100")
    String recharge100(@AuthenticationPrincipal CurrentUser customUser){
        Wallet wallet = walletService.findByUserId(customUser.getUser().getId());
        wallet.setStatus(wallet.getStatus() + 100);
        walletService.updateWallet(wallet);
        createActionRecharged(wallet,100.00);
        return"redirect:/userDetails";
    }

    @GetMapping("/bet")
    String placeBet(@AuthenticationPrincipal CurrentUser customUser){
        Wallet wallet = walletService.findByUserId(customUser.getUser().getId());
        if (wallet.getStatus()>=10) {
            createActionBet(wallet,10.00);
            wallet.setStatus(wallet.getStatus() - 10.00);
            walletService.updateWallet(wallet);
            return "userDetails";
        }else {
            return "redirect:/upcomingEvents";
        }

    }

    private void createActionBet(Wallet wallet,Double amount) {
        Action action = new Action();
        action.setName("Bet");
        action.setAmount(10.00);
        action.setWallet(wallet);
        action.setCreated(LocalDateTime.now());
        actionService.saveAction(action);
    }

    private void createActionRecharged(Wallet wallet, Double amount) {
        Action action = new Action();
        action.setName("Account recharged");
        action.setAmount(amount);
        action.setWallet(wallet);
        action.setCreated(LocalDateTime.now());
        actionService.saveAction(action);
    }
}
