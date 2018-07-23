package pl.coderslab.sportsbetting.controller;

import org.joda.time.DateTime;
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
import pl.coderslab.sportsbetting.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private ActionServiceImpl actionService;

    @Autowired
    private WalletServiceImpl walletService;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private ResultServiceImpl resultService;


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
        Cart cart = cartService.findCartByUserId(user.getId());
        List<Action> actions = new ArrayList<>();
        cart.setActions(actions);
        cartService.saveCart(cart);
        session.invalidate();
        return "logout";
    }

    @GetMapping("/home")
    public String home(Model model){
        List<Game> games = gameService.generateRandomLifeResults();
        model.addAttribute("games",games);
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result){
        DateTime dateOfUser = DateTime.parse(user.getDateOfBirth());
        if(result.hasErrors() || dateOfUser.isAfter(DateTime.now().minusYears(18))) {
            return "register";
        }
        userService.createUser(user);
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
