package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sportsbetting.entity.CurrentUser;
import pl.coderslab.sportsbetting.entity.Role;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.RoleRepository;
import pl.coderslab.sportsbetting.repository.WalletRepository;
import pl.coderslab.sportsbetting.service.UserServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WalletServiceImpl walletService;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
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
//        Role role = roleRepository.findByName("USER");
//        Set<Role> h = new HashSet<Role>();
//        h.add(role);
//        user.setRoles(h);
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setStatus(0.0);
        walletService.createWallet(wallet);
        userService.registerNewUser(user);
        return"redirect:/home";
    }

    @GetMapping("/userDetails")
    public String getUserDetails(@AuthenticationPrincipal CurrentUser customUser, Model model){
        User entityUser = customUser.getUser();
//        Long id = entityUser.getId() ;
//        User user1 = userService.findUserById(id);
        model.addAttribute("user",entityUser);
        Wallet wallet = walletService.findByUserId(entityUser.getId());
        model.addAttribute("wallet", wallet);
        return "userDetails";
    }

}
