package pl.coderslab.sportsbetting.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Game;
import java.util.ArrayList;
import java.util.List;
@Service
public class RestApiService {


    @Autowired
    GameServiceImpl gameService;

//    public RestApiService() {
//        this.regenerateGames();
//    }

    private ArrayList<JSONObject> todayGames = new ArrayList<>();

//    @Scheduled(fixedRate = 10000)
    public void regenerateGames() throws org.json.JSONException {
        todayGames.clear();
        List<Game> allTodayGames = gameService.findAllTodayGames();
        for (Game g: allTodayGames) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("id", g.getId());
            oJsonInner.put("country", g.getCountry());
            oJsonInner.put("place", g.getPlace());
            oJsonInner.put("startingAt",g.getStartingAt());
            oJsonInner.put("finishingAt",g.getFinishingAt());
            oJsonInner.put("results",g.getResults());
            todayGames.add(oJsonInner);
        }
    }
    public ArrayList<JSONObject> getTodayGames() {
        this.regenerateGames();
        return todayGames;
    }
}
