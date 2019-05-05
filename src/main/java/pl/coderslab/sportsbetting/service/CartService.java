package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.User;

public interface CartService {

    void createCart(User user);

    void updateCart(Cart cart);

    Cart findCartByUserId(Long id);

    void clearCart(Long cartId);
}
