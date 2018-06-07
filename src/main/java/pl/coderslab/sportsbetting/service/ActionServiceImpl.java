package pl.coderslab.sportsbetting.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.ActionRepository;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    final
    ActionRepository actionRepository;

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
        actionRepository.deleteById(id);
    }

    @Override
    public List<Action> findActionsByUserId(String name, Long id) {
        return actionRepository.findAllByNameAndWallet_User_Id(name,id);
    }

    @Override
    public List<Action> findAllWhereBet(String name) {
        return actionRepository.findAllByName("Bet");
    }

    public void createActionRecharged(Wallet wallet, Double amount) {
        Action action = new Action();
        action.setName("Account recharged");
        action.setAmount(amount);
        action.setWallet(wallet);
        action.setCreated(LocalDateTime.now());
        actionRepository.save(action);
    }


}
