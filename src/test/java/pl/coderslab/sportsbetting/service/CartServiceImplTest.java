package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.repository.CartRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    private CartServiceImpl cartService;

    @Before
    public void setUp() throws Exception {
        cartService = new CartServiceImpl(cartRepository);
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(cartService,"cartRepository", cartRepository);
    }

    @Test
    public void createCart() {
        cartService.createCart(new User());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void updateCart() {
        cartService.updateCart(new Cart());
        verify(cartRepository,atLeastOnce()).save(any(Cart.class));
    }

    @Test
    public void findCartByUserId() {
        when(cartRepository.findCartByUser_Id(1L)).thenReturn(new Cart());
        Cart result = cartService.findCartByUserId(1L);
        assertNotNull(result);
    }

    @Test
    public void clearCart() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(new Cart()));
        cartService.clearCart(1L);
        verify(cartRepository, times(1)).findById(1L);
    }
}