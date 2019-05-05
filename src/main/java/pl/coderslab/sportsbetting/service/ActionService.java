package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.ActionType;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.Wallet;

import java.util.List;

public interface ActionService {

    void saveAction(Action action);

    List<Action> findAllByWalletId(Long id);

    void deleteById(Long id);

    List<Action> findActionsByUserId(ActionType actionType, Long id);

    List<Action> findAllWhereBet(ActionType actionType);

    List<Action> findAllByCartId(Long cartId);

    Action createBetAction(Long horseId, Cart cart, Double amount);

    Double sumUpCartAndGrantDiscount(List<Action> betsInCart, Wallet wallet);

}
