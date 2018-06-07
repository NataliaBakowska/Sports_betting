package pl.coderslab.sportsbetting.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sportsbetting.service.RestApiService;
import java.util.ArrayList;


@Controller
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    RestApiService restApiService;

    @GetMapping(path= "/live-games")
    public ArrayList<JSONObject> getLiveGamesAction() {
        return restApiService.getTodayGames();
    }

}
