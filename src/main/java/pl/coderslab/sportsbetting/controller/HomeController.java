package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.*;

import java.util.*;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private GameServiceImpl gameService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        List<Game> games = gameService.generateRandomLifeResults();
        model.addAttribute("games",games);
        return "home";
    }

}
