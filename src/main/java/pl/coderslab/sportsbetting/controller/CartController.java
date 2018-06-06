package pl.coderslab.sportsbetting.controller;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.*;
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

    @Autowired
    HorseServiceImpl horseService;

    @GetMapping("/bet/{id}")
    String placeBet(Model model, @PathVariable Long id){
        Action action = new Action();
        model.addAttribute("action",action);
        model.addAttribute("id",id);
        return "bet";
    }

    @PostMapping("/bet/{id}")
    String placeBet(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Action action,
                    @PathVariable Long id){
        Cart cart = cartService.findCartByUserId(customUser.getUser().getId());
        Horse horse = horseService.findHorseById(id);
        List<Action> actions;
        if (cart.getActions() == null){
            actions = new ArrayList<>();
        } else {
            actions = cart.getActions();
        }
        action.setName("Bet");
        action.setCart(cart);
        actions.add(action);
        action.setHorse(horse);
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
    String play(@AuthenticationPrincipal CurrentUser customUser,Model model){
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
        if(sum<=wallet.getStatus()) {
            wallet.setStatus(wallet.getStatus() - sum);
            wallet.setActions(actionsFromWallet);
            walletService.updateWallet(wallet);
            actionsFromCart = new ArrayList<>();
            cart.setActions(actionsFromCart);
            cartService.saveCart(cart);

        }else{
            return "recharge";
        }
        return "redirect:/userDetails";
    }

    @GetMapping("/deleteItem/{id}")
    String deleteItem(@PathVariable Long id){
        actionService.deleteById(id);
        return "redirect:/cart";
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
