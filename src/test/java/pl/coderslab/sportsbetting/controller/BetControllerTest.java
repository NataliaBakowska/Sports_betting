package pl.coderslab.sportsbetting.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.CurrentUser;
import pl.coderslab.sportsbetting.entity.User;
import pl.coderslab.sportsbetting.service.ActionService;
import pl.coderslab.sportsbetting.service.CartService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BetControllerTest {

    @Mock
    private Model model;

    @Mock
    private CurrentUser customUser;

    @Mock
    private Action action;

    @Mock
    private CartService cartService;

    @Mock
    private ActionService actionService;

    BetController betController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        betController = new BetController();
        ReflectionTestUtils.setField(betController, "cartService", cartService);
        ReflectionTestUtils.setField(betController, "actionService", actionService);
    }

    @Test
    public void placeBet() {
        String result = betController.placeBet(model, 1L);
        assertEquals(result, "bet");
    }

    @Test
    public void placeBet1() {
        User user = new User();
        user.setId(1L);
        when(customUser.getUser()).thenReturn(user);
        when(cartService.findCartByUserId(1L)).thenReturn(new Cart());
        String result = betController.placeBet(customUser, 1L, action);
        assertEquals(result,"redirect:/cart");
    }

    @Test
    public void placeBet2() {
        User user = new User();
        user.setId(1L);
        when(customUser.getUser()).thenReturn(user);
        Cart cart = new Cart();
        List<Action> actions = new ArrayList<>();
        actions.add(new Action());
        cart.setActions(actions);
        when(cartService.findCartByUserId(1L)).thenReturn(cart);
        String result = betController.placeBet(customUser, 1L, action);
        assertEquals(result,"redirect:/cart");
    }
}