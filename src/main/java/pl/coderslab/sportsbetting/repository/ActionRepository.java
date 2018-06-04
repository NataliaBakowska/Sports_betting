package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sportsbetting.entity.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
