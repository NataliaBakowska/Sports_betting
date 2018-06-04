package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService{

    final
    WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
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
