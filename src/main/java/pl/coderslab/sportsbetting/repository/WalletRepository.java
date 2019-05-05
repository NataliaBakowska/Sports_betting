package pl.coderslab.sportsbetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sportsbetting.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUserId(Long id);

}
