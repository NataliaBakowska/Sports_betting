package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionService;
import pl.coderslab.sportsbetting.service.CartService;
import pl.coderslab.sportsbetting.service.UserService;
import pl.coderslab.sportsbetting.service.WalletService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    @Autowired
    ActionService actionService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(user);

        } catch (IllegalArgumentException e){
            e.printStackTrace();
            return "register";
        }
        return"redirect:/home";
    }

    @GetMapping("/userDetails")
    public String getUserDetails(@AuthenticationPrincipal CurrentUser customUser, Model model){
        User entityUser = customUser.getUser();
        model.addAttribute("user",entityUser);
        Wallet wallet = walletService.findByUserId(entityUser.getId());
        model.addAttribute("wallet", wallet);
        List<Action> actions = actionService.findAllByWalletId(wallet.getId());
        model.addAttribute("actions",actions);
        return "userDetails";
    }

}
