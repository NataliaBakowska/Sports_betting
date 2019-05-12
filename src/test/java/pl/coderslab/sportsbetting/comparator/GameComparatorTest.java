package pl.coderslab.sportsbetting.comparator;

import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.sportsbetting.entity.Game;
import pl.coderslab.sportsbetting.entity.Result;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameComparatorTest {

    GameComparator gameComparator;
    @Mock Game game1;
    @Mock Game game2;

    @Before
    public void setUp() throws Exception {
        gameComparator = new GameComparator();
    }

    /**
     * When game1 before game2
     */
    @Test
    public void compareGame1BeforeGame2() {
        when(game1.getStartingAt()).thenReturn(DateUtil.parse("2019-10-10"));
        when(game2.getStartingAt()).thenReturn(DateUtil.parse("2019-10-11"));
        int value = gameComparator.compare(game1,game2);
        assertEquals(value, -1);
    }

    /**
     * When game1 before game2
     */
    @Test
    public void compareGame1AfterGame2() {
        when(game1.getStartingAt()).thenReturn(DateUtil.parse("2019-10-11"));
        when(game2.getStartingAt()).thenReturn(DateUtil.parse("2019-10-10"));
        int value = gameComparator.compare(game1,game2);
        assertEquals(value, 1);
    }

    /**
     * When game1 before game2
     */
    @Test
    public void compareGame1WhenGame2() {
        when(game1.getStartingAt()).thenReturn(DateUtil.parse("2019-10-10"));
        when(game2.getStartingAt()).thenReturn(DateUtil.parse("2019-10-10"));
        int value = gameComparator.compare(game1,game2);
        assertEquals(value, 0);
    }
}