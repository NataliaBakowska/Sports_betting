package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    WalletRepository walletRepository;

    @Override
    public void createWallet(User user) {
        walletRepository.save(new Wallet(user));
    }

    @Override
    public Wallet findByUserId(Long id) {
        return walletRepository.findByUser_Id(id);
    }

    @Override
    public void updateWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
