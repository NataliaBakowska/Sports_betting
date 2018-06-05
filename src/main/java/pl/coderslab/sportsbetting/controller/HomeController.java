package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.CartServiceImpl;
import pl.coderslab.sportsbetting.service.UserServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ActionServiceImpl actionService;

    @Autowired
    private WalletServiceImpl walletService;

    @Autowired
    private CartServiceImpl cartService;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/logout")
    public String logout(HttpSession session, @AuthenticationPrincipal CurrentUser customUser){
        User user = customUser.getUser();
        Cart cart = user.getCart();
        List<Action> actions = new ArrayList<>();
        cart.setActions(actions);
        cartService.saveCart(cart);
        session.invalidate();

        return "logout";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        user.setEnabled(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setStatus(0.0);
        walletService.createWallet(wallet);
        Cart cart = new Cart();
        cart.setUser(user);
//        List<Action> actions = new ArrayList<>();
//        cart.setActions(actions);
        cartService.saveCart(cart);
        userService.registerNewUser(user);
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
