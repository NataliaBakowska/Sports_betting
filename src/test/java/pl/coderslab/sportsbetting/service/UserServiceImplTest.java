package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.repository.RoleRepository;
import pl.coderslab.sportsbetting.repository.UserRepository;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private WalletServiceImpl walletService;

    @Mock
    private CartServiceImpl cartService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(walletService, cartService, userRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void registerNewUser() {
        userService.registerNewUser(new User());
        verify(userRepository,times(1)).save(any(User.class));
    }

    @Test
    public void findByUserName() {
        when(userRepository.findByUsername("username")).thenReturn(new User());
        User result= userService.findByUserName("username");
        assertNotNull(result);
    }

    @Test
    public void findUserById() {
        when(userRepository.findOneById(1L)).thenReturn(new User());
        User result= userService.findUserById(1L);
        assertNotNull(result);
    }

    @Test
    public void saveUser() {
        userService.saveUser(new User());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setPassword("123");
        user.setDateOfBirth("2000-01-10");
        userService.createUser(user);
        verify(passwordEncoder,times(1)).encode(anyString());
        verify(walletService,times(1)).createWallet(user);
        verify(cartService,times(1)).createCart(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser2() {
        User user = new User();
        user.setPassword("123");
        user.setDateOfBirth("2010-01-10");
        userService.createUser(user);
        verify(passwordEncoder,times(1)).encode(anyString());
        verify(walletService,times(1)).createWallet(user);
        verify(cartService,times(1)).createCart(user);
    }
}