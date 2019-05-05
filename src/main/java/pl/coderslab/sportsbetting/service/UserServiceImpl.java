package pl.coderslab.sportsbetting.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.Role;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.repository.RoleRepository;
import pl.coderslab.sportsbetting.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private WalletService walletService;

    private CartService cartService;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(WalletServiceImpl walletService, CartServiceImpl cartService, UserRepository userRepository, RoleRepository roleRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.walletService = walletService;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void createUser(User user) throws IllegalArgumentException {
        DateTime dateOfUser = DateTime.parse(user.getDateOfBirth());
        if(dateOfUser.isAfter(DateTime.now().minusYears(18))) {
            throw new IllegalArgumentException();
        }
        user.setEnabled(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        walletService.createWallet(user);
        cartService.createCart(user);
        registerNewUser(user);
    }
}
