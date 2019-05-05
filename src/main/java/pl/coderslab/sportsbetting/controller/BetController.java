package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionService;
import pl.coderslab.sportsbetting.service.CartService;
import pl.coderslab.sportsbetting.service.HorseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BetController {

    @Autowired
    CartService cartService;

    @Autowired
    HorseService horseService;

    @Autowired
    ActionService actionService;

    @GetMapping("/bet/{id}")
    String placeBet(Model model, @PathVariable Long id){
        Action action = new Action();
        model.addAttribute("action",action);
        model.addAttribute("id",id);
        return "bet";
    }

    @PostMapping("/bet/{id}")
    String placeBet(@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id, @ModelAttribute Action action){
        Cart cart = cartService.findCartByUserId(customUser.getUser().getId());
        List<Action> actions;
        if (cart.getActions() == null){
            actions = new ArrayList<>();
        } else {
            actions = cart.getActions();
        }
        actions.add(actionService.createBetAction(id, cart, action.getAmount()));
        cart.setActions(actions);
        cartService.updateCart(cart);
        return "redirect:/cart";
    }
}
