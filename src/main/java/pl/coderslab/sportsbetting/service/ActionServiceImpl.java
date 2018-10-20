package pl.coderslab.sportsbetting.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.ActionType;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.ActionRepository;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    final
    ActionRepository actionRepository;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
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

    public void createActionRecharged(Wallet wallet, Double amount) {
        Action action = new Action();
        action.setActionType(ActionType.RECHARGE);
        action.setAmount(amount);
        action.setWallet(wallet);
        action.setCreated(LocalDateTime.now());
        actionRepository.save(action);
    }


}
