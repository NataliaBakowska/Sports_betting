package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sportsbetting.entity.Action;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {

    List<Action> findAllByWallet_IdOrderByCreatedDesc(Long id);

}
