package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sportsbetting.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    Game findGameById(Long id);
}
