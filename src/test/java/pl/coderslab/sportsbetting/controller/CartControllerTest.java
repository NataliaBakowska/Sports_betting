package pl.coderslab.sportsbetting.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.CurrentUser;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.service.ActionService;
import pl.coderslab.sportsbetting.service.CartService;
import pl.coderslab.sportsbetting.service.WalletService;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    private CartController cartController;

    @Mock
    private CurrentUser currentUser;

    @Mock
    private Model model;

    @Mock
    private CartService cartService;

    @Mock
    private ActionService actionService;

    @Mock
    private WalletService walletService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cartController = new CartController();
        ReflectionTestUtils.setField(cartController,"cartService", cartService);
        ReflectionTestUtils.setField(cartController,"actionService", actionService);
        ReflectionTestUtils.setField(cartController,"walletService", walletService);
    }

    @Test
    public void seeCart() {
        User user = new User();
        user.setUsername("username");
        user.setId(1L);
        when(currentUser.getUser()).thenReturn(user);
        Cart cart = new Cart();
        cart.setActions(new ArrayList<>());
        when(cartService.findCartByUserId(1L)).thenReturn(cart);
        String result = cartController.seeCart(currentUser, model);
        assertEquals("cart", result);
    }

    @Test
    public void play() {
        User user = new User();
        user.setUsername("username");
        user.setId(1L);
        when(currentUser.getUser()).thenReturn(user);
        Cart cart = new Cart();
        cart.setActions(new ArrayList<>());
        Wallet wallet = new Wallet();
        wallet.setStatus(1000000.0);
        wallet.setActions(new ArrayList<>());

        when(walletService.findByUserId(1L)).thenReturn(wallet);
        when(cartService.findCartByUserId(1L)).thenReturn(cart);
        String result = cartController.play(currentUser);
        assertEquals("redirect:/userDetails", result);
    }

    @Test
    public void play2() {
        User user = new User();
        user.setUsername("username");
        user.setId(1L);
        when(currentUser.getUser()).thenReturn(user);
        Cart cart = new Cart();
        cart.setActions(new ArrayList<>());
        Wallet wallet = new Wallet();
        wallet.setStatus(-5.0);
        wallet.setActions(new ArrayList<>());

        when(walletService.findByUserId(1L)).thenReturn(wallet);
        when(cartService.findCartByUserId(1L)).thenReturn(cart);
        String result = cartController.play(currentUser);
        assertEquals("recharge", result);
    }

    @Test
    public void deleteItem() {
        String result = cartController.deleteItem(1L);
        assertEquals("redirect:/cart", result);
    }
}