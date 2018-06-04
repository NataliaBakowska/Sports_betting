package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Action;
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
        return actionRepository.findAllByWallet_Id(id);
    }
}
