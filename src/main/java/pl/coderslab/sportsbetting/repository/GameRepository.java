package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.sportsbetting.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    Game findGameById(Long id);

    @Query("Select g from Game g where g.startingAt not like current_date")
    List<Game> findGamesByStartingAtAfterToday();

    @Query(value = "select * from game where DATE(starting_at) = curdate()", nativeQuery = true)
    List<Game> findTodayGames();

}
