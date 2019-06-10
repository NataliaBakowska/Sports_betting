package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.ActionType;
import pl.coderslab.sportsbetting.entity.Cart;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.ActionRepository;
import pl.coderslab.sportsbetting.repository.HorseRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionServiceTest {

    private ActionService service;

    private ActionServiceImpl serviceImpl;

    @Mock
    private ActionRepository repository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private HorseRepository horseRepository;

    @Before
    public void setUp() {
        service = new ActionServiceImpl(repository, jdbcTemplate, horseRepository);
        serviceImpl = new ActionServiceImpl(repository, jdbcTemplate, horseRepository);
    }

    /**
     * {@link ActionServiceImpl#saveAction(Action)}
     */
    @Test
    public void saveActionTest() {
        Action action = new Action();
        service.saveAction(action);
        verify(repository, times(1)).save(action);
    }

    /**
     * {@link ActionServiceImpl#findAllByWalletId(Long)}
     */
    @Test
    public void findAllByWalletIdTest() {
        // given
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        List<Action> actions = new ArrayList<>();
        Action action = new Action();
        action.setWallet(wallet);
        action.setId(1L);
        actions.add(action);
        when(repository.findAllByWallet_IdOrderByCreatedDesc(wallet.getId())).thenReturn(actions);
        // when
        List<Action> actions1 = service.findAllByWalletId(1L);
        // then
        assertEquals(actions1.get(0).getWallet(),wallet);
    }

    /**
     * {@link ActionServiceImpl#deleteById(Long)}
     */
    @Test
    public void deleteByIdTest() {
        service.deleteById(1L);
    }

    /**
     * {@link ActionServiceImpl#findActionsByUserId(ActionType, Long)}
     */
    @Test
    public void findActionsByUserIdTest() {
        service.findActionsByUserId(ActionType.BET, 1L);
        verify(repository, times(1))
                .findAllByActionTypeAndWallet_User_Id(ActionType.BET, 1L);
    }

    @Test
    public void when_name_bet_then_return_list(){
        //given
        List<Action> actions = new ArrayList<>();
        Action action = new Action();
        action.setActionType(ActionType.BET);
        actions.add(action);
        when(repository.findAllByActionType(ActionType.BET)).thenReturn(actions);
        //when
        List<Action> actions1 = service.findAllWhereBet(ActionType.BET);
        //then
        assertEquals(actions1.get(0),action);
    }

    /**
     * {@link ActionServiceImpl#findAllByCartId(Long)}
     */
    @Test
    public void findallByCartIdTest() {
        List<Action> result = service.findAllByCartId(5L);
        assertNull(result);
    }

    /**
     * {@link ActionServiceImpl#createBetAction(Long, Cart, Double)}
     */
    @Test
    public void createBetActionTest() {
        Action result = service.createBetAction(10L, new Cart(), 100.0);
        assertTrue(result.getActionType() == ActionType.BET);
    }

    /**
     * {@link ActionServiceImpl#createActionRecharged(Wallet, Double)}
     */
    @Test
    public void createActionRechargedTest() {
        serviceImpl.createActionRecharged(new Wallet(), 100.0);
        verify(repository, times(1)).save(any(Action.class));
    }

    /**
     * {@link ActionServiceImpl#sumUpCartAndGrantDiscount(List, Wallet)}
     */
    @Test
    public void sumUpCartAndGrantDiscountTest() {
        List<Action> actions = new ArrayList<>();
        Action action = new Action();
        action.setAmount(5.0);
        actions.add(action);
        Double result = serviceImpl.sumUpCartAndGrantDiscount(actions, new Wallet());
        assertEquals(5, (double) result, 0.0);
    }

    /**
     * {@link ActionServiceImpl#sumUpCartAndGrantDiscount(List, Wallet)}
     */
    @Test
    public void sumUpCartAndGrantDiscountTest2() {
        List<Action> actions = new ArrayList<>();

        Action action = new Action();
        action.setAmount(5.0);
        Action action2 = new Action();
        action2.setAmount(5.0);
        Action action3 = new Action();
        action3.setAmount(5.0);
        Action action4 = new Action();
        action4.setAmount(5.0);

        actions.add(action);
        actions.add(action2);
        actions.add(action3);
        actions.add(action4);
        Double result = serviceImpl.sumUpCartAndGrantDiscount(actions, new Wallet());
        assertTrue(20.0 * 0.85 == result);
    }
}