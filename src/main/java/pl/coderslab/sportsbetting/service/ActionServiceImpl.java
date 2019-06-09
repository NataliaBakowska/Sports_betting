package pl.coderslab.sportsbetting.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.*;
import pl.coderslab.sportsbetting.repository.ActionRepository;
import pl.coderslab.sportsbetting.repository.HorseRepository;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository, JdbcTemplate jdbcTemplate, HorseRepository horseRepository) {
        this.actionRepository = actionRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.horseRepository = horseRepository;
    }

    @Override
    public void saveAction(Action action) {
        actionRepository.save(action);
    }

    @Override
    public List<Action> findAllByWalletId(Long id) {
        return actionRepository.findAllByWallet_IdOrderByCreatedDesc(id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM Action WHERE id=" + id);
    }

    @Override
    public List<Action> findActionsByUserId(ActionType actionType, Long id) {
        return actionRepository.findAllByActionTypeAndWallet_User_Id(actionType,id);
    }

    @Override
    public List<Action> findAllWhereBet(ActionType actionType) {
        return actionRepository.findAllByActionType(ActionType.BET);
    }

    @Override
    public List<Action> findAllByCartId(Long cartId) {
        return null;
    }

    @Override
    public Action createBetAction(Long horseId, Cart cart, Double amount) {
        Action  action = new Action();
        action.setActionType(ActionType.BET);
        action.setCart(cart);
        action.setHorse(horseRepository.findHorseById(horseId));
        action.setAmount(amount);
        return action;
    }

    public void createActionRecharged(Wallet wallet, Double amount) {
        Action action = new Action();
        action.setActionType(ActionType.RECHARGE);
        action.setAmount(amount);
        action.setWallet(wallet);
        action.setCreated(LocalDateTime.now());
        actionRepository.save(action);
    }

    public Double sumUpCartAndGrantDiscount(List<Action> betsInCart, Wallet wallet) {
        Double sum = 0.0;
        betsInCart.forEach(action -> {
            action.setCreated(LocalDateTime.now());
            action.setCart(null);
            action.setWallet(wallet);
        });
        for (Action action : betsInCart) {
            sum += action.getAmount();
        }
        if (betsInCart.size()>3){
            sum = sum*0.85;
        }
        return sum;
    }


}
