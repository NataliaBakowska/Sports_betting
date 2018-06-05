package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Cart;

public interface CartService {

    void saveCart(Cart cart);

    Cart findCartByUserId(Long id);
}
