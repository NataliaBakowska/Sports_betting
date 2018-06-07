package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Action;

import java.util.List;

public interface ActionService {

    void saveAction(Action action);

    List<Action> findAllByWalletId(Long id);

    void deleteById(Long id);

    List<Action> findActionsByUserId(String name, Long id);

    List<Action> findAllWhereBet(String name);

}
