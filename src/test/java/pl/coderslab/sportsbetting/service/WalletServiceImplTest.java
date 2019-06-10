package pl.coderslab.sportsbetting.service;

import org.hibernate.validator.constraints.UniqueElements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.WalletRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WalletServiceImplTest {

    private WalletServiceImpl walletService;

    @Mock
    private WalletRepository walletRepository;

    @Before
    public void setUp() throws Exception {
        walletService = new WalletServiceImpl();
        ReflectionTestUtils.setField(walletService,"walletRepository", walletRepository);
    }

    @Test
    public void createWallet() {
        walletService.createWallet(new User());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    public void findByUserId() {
        when(walletRepository.findByUserId(1L)).thenReturn(new Wallet());

        Wallet result = walletService.findByUserId(1L);
        assertNotNull(result);
    }

    @Test
    public void updateWallet() {
        walletService.updateWallet(new Wallet());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    public void rechargeWallet() {
        Wallet wallet = new Wallet();
        wallet.setStatus(10.00);
        when(walletRepository.findByUserId(1L)).thenReturn(wallet);

        Wallet result = walletService.rechargeWallet(1L, 100);
        assertNotNull(result);
    }
}