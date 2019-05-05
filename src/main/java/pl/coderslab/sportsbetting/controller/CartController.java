package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.*;
import org.springframework.ui.Model;

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
        Cart cart = cartService.findCartByUserId(customUser.getUser().getId());
        List<Action> betsInCart = cart.getActions();
        Wallet wallet = walletService.findByUserId(customUser.getUser().getId());
        List<Action> actionsFromWallet = wallet.getActions();
        Double sum = actionService.sumUpCartAndGrantDiscount(betsInCart, wallet);
        actionsFromWallet.addAll(betsInCart);

        if(sum <= wallet.getStatus()) {
            wallet.setStatus(wallet.getStatus() - sum);
            wallet.setActions(actionsFromWallet);
            walletService.updateWallet(wallet);
            cartService.clearCart(cart.getId());
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

}
