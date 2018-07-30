package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.ActionType;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {

    List<Action> findAllByWallet_IdOrderByCreatedDesc(Long id);

    List<Action> findAllByActionTypeAndWallet_User_Id(ActionType actionType, Long id);

    List<Action> findAllByActionType(ActionType actionType);


}
