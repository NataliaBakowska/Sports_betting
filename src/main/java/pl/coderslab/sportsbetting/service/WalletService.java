package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Wallet;

public interface WalletService {

    void createWallet(Wallet wallet);

    Wallet findByUserId(Long id);

    void updateWallet(Wallet wallet);
}
