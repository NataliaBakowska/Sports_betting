package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void createCart(User user) {
        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUserId(Long id) {
        return cartRepository.findCartByUser_Id(id);
    }

    @Override
    public void clearCart(Long cartId) {
        List<Action> actions = new ArrayList<>();
        Cart cartToUpdate = cartRepository.findById(cartId).get();
        cartToUpdate.setActions(actions);
        updateCart(cartToUpdate);
    }
}
