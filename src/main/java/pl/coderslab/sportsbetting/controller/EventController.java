package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.service.FakerService;
import pl.coderslab.sportsbetting.service.GameServiceImpl;
import pl.coderslab.sportsbetting.service.HorseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class EventController {
    @Autowired
    FakerService fakerService;

    @Autowired
    GameServiceImpl gameService;

    @Autowired
    HorseServiceImpl horseService;

    Random rand = new Random();

    @GetMapping("/upcomingEvents")
    String showUpcomingEvents(Model model){
        List<Game> games = new ArrayList<>();
        for(int i = 1; i<rand.nextInt(10)+5;i++){
            Game game = fakerService.generateGame();
            List<Horse> horses = game.getHorses();
            for(int j = 0; j<horses.size();j++){
                horseService.saveHorse(horses.get(j));
            }
            gameService.saveGame(game);
            games.add(game);
        }
        model.addAttribute("game", games);
        return "events";
    }

    @GetMapping("/eventDetails/{id}")
    String showEventDetails(@PathVariable Long id, Model model){
        Game game = gameService.findEventById(id);
        model.addAttribute("game",game);
        List<Horse> horses = game.getHorses();
        model.addAttribute("horses",horses);
        return "eventDetails";
    }
}
