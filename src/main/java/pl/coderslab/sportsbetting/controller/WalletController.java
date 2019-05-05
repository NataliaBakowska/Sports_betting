package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.sportsbetting.entity.CurrentUser;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;

@Controller
public class WalletController {

    @Autowired
    WalletServiceImpl walletService;

    @Autowired
    ActionServiceImpl actionService;

    @GetMapping("/recharge/10")
    String recharge10(@AuthenticationPrincipal CurrentUser user){
        Wallet wallet = walletService.rechargeWallet(user.getUser().getId(), 10);
        actionService.createActionRecharged(wallet,10.00);
        return"redirect:/userDetails";
    }



    @GetMapping("/recharge/20")
    String recharge20(@AuthenticationPrincipal CurrentUser user){
        Wallet wallet = walletService.rechargeWallet(user.getUser().getId(), 20);
        actionService.createActionRecharged(wallet,20.00);
        return"redirect:/userDetails";
    }

    @GetMapping("/recharge/50")
    String recharge50(@AuthenticationPrincipal CurrentUser user){
        Wallet wallet = walletService.rechargeWallet(user.getUser().getId(), 50);
        actionService.createActionRecharged(wallet,50.00);
        return"redirect:/userDetails";
    }

    @GetMapping("/recharge/100")
    String recharge100(@AuthenticationPrincipal CurrentUser user){
        Wallet wallet = walletService.rechargeWallet(user.getUser().getId(), 100);
        actionService.createActionRecharged(wallet,100.00);
        return"redirect:/userDetails";
    }



}
