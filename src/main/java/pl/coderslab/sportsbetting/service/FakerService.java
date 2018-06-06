package pl.coderslab.sportsbetting.service;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Horse;
import org.joda.time.LocalDateTime;
import pl.coderslab.sportsbetting.entity.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class FakerService {

    public FakerService() throws JSONException{
    }
    public ArrayList<JSONObject> getTodayGames() {
        return todayGames;
    }

    Random random = new Random();

    String[] breeds = {"XX","OO"};

    LocalDateTime ld = new LocalDateTime();

    private ArrayList<JSONObject> todayGames = new ArrayList<>();

    @Autowired
    GameServiceImpl gameService;

    @Autowired
    HorseServiceImpl horseService;

    @Autowired
    ResultServiceImpl resultService;

//    @Scheduled(fixedRate = 5000)
//    public void regenerate() throws JSONException {
//        Faker faker = new Faker();
//        todayGames.clear();
//        for (int i = 0; i < 10; i++) {
//            JSONObject oJsonInner = new JSONObject();
//            oJsonInner.put("firstTeam", faker.team().name());
//            oJsonInner.put("firstPoints", faker.number().randomDigitNotZero());
//            oJsonInner.put("secondTeam", faker.team().name());
//            oJsonInner.put("secondPoints", faker.number().randomDigitNotZero());
//            oJsonInner.put("sport", faker.team().sport());
//            todayGames.add(oJsonInner);
//        }}

    public Game generateGame() throws JSONException {
        Faker faker = new Faker();
        Game game = new Game();
//        List <Result> results = new ArrayList<>();
        game.setCountry(faker.address().country());
        game.setPlace(faker.address().cityName());
        gameService.saveGame(game);
        gameService.saveGame(game);
        List<Horse> horses = generateHorses();
        List<Result> results = new ArrayList<>();
        for(int i = 0; i<horses.size();i++) {
            Horse horse = horses.get(i);
            Result result = new Result();
            result.setHorse(horse);
            results.add(result);
            //horse.setGame(game);
            horseService.saveHorse(horse);
        }
        for(Result r : results){
            r.setGame(game);
            resultService.saveResult(r);
        }
        game.setStartingAt(
                (faker.date().between(LocalDateTime.now().plusDays(1).toDate(),ld.plusDays(7).toDate())));
        gameService.saveGame(game);
        return game;
    }

    public Game generateCurrentGame() throws JSONException {
        Faker faker = new Faker();
        Game game = new Game();
        game.setCountry(faker.address().country());
        game.setPlace(faker.address().cityName());
        gameService.saveGame(game);
        List<Horse> horses = generateHorses();
        List<Result> results = new ArrayList<>();
        for(int i = 0; i<horses.size();i++) {
            Horse horse = horses.get(i);
            Result result = new Result();
            result.setHorse(horse);
            results.add(result);
            //horse.setGame(game);
            horseService.saveHorse(horse);
        }
        for(Result r : results){
            r.setGame(game);
            resultService.saveResult(r);
        }
        game.setStartingAt(LocalDateTime.now().toDate());
        gameService.saveGame(game);
        return game;
    }

    public List<Horse> generateHorses() throws JSONException {
        Faker faker = new Faker();
        String breed = breeds[random.nextInt(1)+0];
        int yearOfBirth = random.nextInt(4)+2012;
        List<Horse> horses = new ArrayList<>();
        for (int i =0; i<random.nextInt(3)+5;i++){
            Horse horse = new Horse();
//            List<Result> results = new ArrayList<>();
//            for(Result r : results){
//                r.setGame(game);
//            }
            horse.setName(faker.pokemon().name());
            horse.setBreed(breed);
            horse.setBreeder(faker.name().fullName());
            horse.setOwner(faker.name().fullName());
            horse.setJockey(faker.name().fullName());
            horse.setYearOfBirth(yearOfBirth);
//            horse.setResults(results);
            horses.add(horse);

        }
        return horses;
    }

}
