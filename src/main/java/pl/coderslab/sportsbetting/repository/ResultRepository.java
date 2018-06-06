package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sportsbetting.entity.Result;

import java.util.List;


@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByGame_Id(Long id);

    Result findByGame_IdAndHorse_Id(Long gameId, Long horseId);
}
