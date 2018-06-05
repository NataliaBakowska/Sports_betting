package pl.coderslab.sportsbetting.controller;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.CartServiceImpl;
import pl.coderslab.sportsbetting.service.UserServiceImpl;
import pl.coderslab.sportsbetting.service.WalletService;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    WalletService walletService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ActionServiceImpl actionService;

    @Autowired
    CartServiceImpl cartService;

    @GetMapping("/bet")
    String placeBet(Model model){
        Action action = new Action();
        model.addAttribute("action",action);
        return "bet";
    }

    @PostMapping("/bet")
    String placeBet(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Action action){
        Cart cart = cartService.findCartByUserId(customUser.getUser().getId());
        List<Action> actions;
        if (cart.getActions() == null){
            actions = new ArrayList<>();
        } else {
            actions = cart.getActions();
        }
        action.setName("Bet");
        action.setCart(cart);
        actions.add(action);
        cart.setActions(actions);
        cartService.saveCart(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    String seeCart(@AuthenticationPrincipal CurrentUser customUser,Model model){
        User user = customUser.getUser();
        String name = user.getUsername();
        Cart cart = customUser.getUser().getCart();
        List<Action> actions = cart.getActions();
        model.addAttribute("name",name);
        model.addAttribute("actions",actions);
        return "cart";
    }

    @GetMapping("/play")
    String play(@AuthenticationPrincipal CurrentUser customUser){
        User user = customUser.getUser();
        Cart cart = user.getCart();
        List<Action> actionsFromCart = cart.getActions();
        Wallet wallet = user.getWallet();
        List<Action> actionsFromWallet = wallet.getActions();
        Double sum = 0.0;
        for (Action action : actionsFromCart) {
                action.setCreated(LocalDateTime.now());
                action.setWallet(wallet);
                action.setCart(null);
                sum += action.getAmount();
                actionsFromWallet.add(action);
        }
        wallet.setStatus(wallet.getStatus() - sum);
        wallet.setActions(actionsFromWallet);
        walletService.updateWallet(wallet);
        actionsFromCart = new ArrayList<>();
        cart.setActions(actionsFromCart);
        cartService.saveCart(cart);
        return "redirect:/userDetails";
    }

    public void createActionBet(Wallet wallet,Double amount) {
        Action action = new Action();
        action.setName("Bet");
        action.setAmount(10.00);
        action.setWallet(wallet);
        action.setCreated(LocalDateTime.now());
        actionService.saveAction(action);
    }
}
