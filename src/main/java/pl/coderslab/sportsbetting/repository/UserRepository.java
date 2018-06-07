package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sportsbetting.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findOneById(Long id);

    User findByUsername(String username);

}
