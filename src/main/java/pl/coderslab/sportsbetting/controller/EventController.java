package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.sportsbetting.service.FakerService;

@Controller
public class EventController {
    @Autowired
    FakerService fakerService;

    @GetMapping("/upcomingEvents")
    String showUpcomingEvents(Model model){
        fakerService.generateGame();
        return "events";
    }
}
