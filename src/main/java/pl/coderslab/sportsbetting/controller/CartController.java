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
    UserService userService;

    @Autowired
    ActionService actionService;

    @Autowired
    CartService cartService;

    @Autowired
    HorseService horseService;

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
        action.setActionType(ActionType.BET);
        action.setCart(cart);
        actions.add(action);
        action.setHorse(horse);
        cart.setActions(actions);
        cartService.updateCart(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    String seeCart(@AuthenticationPrincipal CurrentUser customUser, Model model){

        User user = customUser.getUser();
        String name = user.getUsername();
        Cart cart = cartService.findCartByUserId(user.getId());
        List<Action> actions = cart.getActions();
        model.addAttribute("name",name);
        model.addAttribute("actions",actions);
        return "cart";
    }

    @GetMapping("/play")
    String play(@AuthenticationPrincipal CurrentUser customUser){
        User user = customUser.getUser();
        Cart cart = cartService.findCartByUserId(user.getId());
        List<Action> actionsFromCart = cart.getActions();
        Wallet wallet = walletService.findByUserId(user.getId());
        List<Action> actionsFromWallet = wallet.getActions();
        Double sum = 0.0;
        for (Action action : actionsFromCart) {
                action.setCreated(LocalDateTime.now());
                action.setWallet(wallet);
                action.setCart(null);
                sum += action.getAmount();
                actionsFromWallet.add(action);
        }
        if (actionsFromCart.size()>3){
            sum = sum*0.85;
        }
        if(sum<=wallet.getStatus()) {
            wallet.setStatus(wallet.getStatus() - sum);
            wallet.setActions(actionsFromWallet);
            walletService.updateWallet(wallet);
            actionsFromCart = new ArrayList<>();
            cart.setActions(actionsFromCart);
            cartService.updateCart(cart);

        }else{
            return "recharge";
        }
        return "redirect:/userDetails";
    }

    @GetMapping("/deleteItem/{id}")
    String deleteItem(@PathVariable Long id){
        System.out.println(id);
        actionService.deleteById(id);
        return "redirect:/cart";
    }

}
