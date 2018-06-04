package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sportsbetting.entity.Horse;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Long> {
}
