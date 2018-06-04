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
        game.setCountry(faker.address().country());
        game.setPlace(faker.address().cityName());
        List<Horse> horses = generateHorses();
        int size = horses.size();
        for(int i = 0; i<size;i++){
            horseService.saveHorse(horses.get(i));
        }
        game.setHorses(horses);
        game.setStartingAt(
                (faker.date().between(LocalDateTime.now().toDate(),ld.plusDays(7).toDate())));
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
            horse.setName(faker.pokemon().name());
            horse.setBreed(breed);
            horse.setBreeder(faker.name().fullName());
            horse.setOwner(faker.name().fullName());
            horse.setJockey(faker.name().fullName());
            horse.setYearOfBirth(yearOfBirth);
            horses.add(horse);
        }
        return horses;
    }

}
