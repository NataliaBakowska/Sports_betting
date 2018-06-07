package pl.coderslab.sportsbetting.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import pl.coderslab.sportsbetting.dto.GameDto;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.service.ActionServiceImpl;
import pl.coderslab.sportsbetting.service.GameService;
import pl.coderslab.sportsbetting.service.HorseServiceImpl;
import pl.coderslab.sportsbetting.service.WalletServiceImpl;
//import pl.coderslab.sportsbetting.service.RestApiService;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api")
public class RestApiController {
//
//    @Autowired
//    RestApiService restApiService;

    @Autowired
    GameService gameService;

    @Autowired
    HorseServiceImpl horseService;

    @Autowired
    ActionServiceImpl actionService;

    @Autowired
    WalletServiceImpl walletService;

    @GetMapping(path= "/live-games")
    @ResponseBody
    public List<Game> getLiveGamesAction() {
        return gameService.findAllTodayGames();
    }

    @GetMapping(path = "/upcoming-events")
    @ResponseBody
    public List<Game> getUpcoming(){
        return gameService.findAllFutureGames();
    }

    @GetMapping(path = "/event/{id}")
    @ResponseBody
    public Game findGameById(@PathVariable Long id){
        return gameService.findEventById(id);
    }

    @GetMapping(path = "/horse/{id}")
    @ResponseBody
    public Horse findById(@PathVariable Long id){
        return horseService.findHorseById(id);
    }

    @GetMapping(path="/actions-user")
    @ResponseBody
    public List<Action> findAction(@AuthenticationPrincipal CurrentUser currentUser){
        Wallet wallet = currentUser.getUser().getWallet();
        return actionService.findAllByWalletId(wallet.getId());
    }

    @GetMapping(path="/cart")
    @ResponseBody
    public Cart findCart(@AuthenticationPrincipal CurrentUser currentUser){
        Cart cart = currentUser.getUser().getCart();
        return cart;
    }

}
