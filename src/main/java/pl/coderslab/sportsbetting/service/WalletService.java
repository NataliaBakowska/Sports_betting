package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.entity.Wallet;

public interface WalletService {

    void createWallet(User user);

    Wallet findByUserId(Long id);

    void updateWallet(Wallet wallet);

    Wallet rechargeWallet(Long userId, int amount);
}
