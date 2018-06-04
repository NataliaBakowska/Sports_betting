package pl.coderslab.sportsbetting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.sportsbetting.service.FakerService;

@Controller
public class RestController {

    @Autowired
    FakerService fakerService;

    @GetMapping(path= "/fake-today-games")
    @ResponseBody
    public String sample() {
        return fakerService.getTodayGames().toString();
    }
}
