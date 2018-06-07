package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.sportsbetting.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    Game findGameById(Long id);

    @Query(value = "select * from game where TIMESTAMP(starting_at) > now()", nativeQuery = true)
    List<Game> findGamesByStartingAtAfterToday();

    @Query(value = "select * from game where TIMESTAMP(starting_at) < now() and TIMESTAMP (finishing_at) > now()"
            , nativeQuery = true)
    List<Game> findTodayGames();

    @Query(value = "select * from game where TIMESTAMP(finishing_at) < now()", nativeQuery = true)
    List<Game> findPastGames();

}
