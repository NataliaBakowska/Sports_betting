package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUserId(Long id) {
        return cartRepository.findCartByUser_Id(id);
    }
}
