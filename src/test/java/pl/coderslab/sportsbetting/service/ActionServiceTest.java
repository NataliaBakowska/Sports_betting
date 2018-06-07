package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.sportsbetting.entity.Action;
import pl.coderslab.sportsbetting.entity.Wallet;
import pl.coderslab.sportsbetting.repository.ActionRepository;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ActionServiceTest {

    private ActionService service;

    @Mock
    private ActionRepository repository;

    @Before
    public void setUp() {
        service = new ActionServiceImpl(repository);
    }

    @Test
    public void when_searching_by_wallet_then_return_list() {
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

    @Test
    public void when_name_bet_then_return_list(){
        //given
        List<Action> actions = new ArrayList<>();
        Action action = new Action();
        action.setName("Bet");
        actions.add(action);
        when(repository.findAllByName("Bet")).thenReturn(actions);
        //when
        List<Action> actions1 = service.findAllWhereBet("Bet");
        //then
        assertEquals(actions1.get(0),action);

    }

}