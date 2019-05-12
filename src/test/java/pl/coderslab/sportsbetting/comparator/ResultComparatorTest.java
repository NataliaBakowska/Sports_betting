package pl.coderslab.sportsbetting.comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.sportsbetting.entity.Result;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultComparatorTest {

    ResultComparator resultComparator;
    @Mock  Result result1;
    @Mock Result result2;

    @Before
    public void setUp() throws Exception {
        resultComparator = new ResultComparator();
    }

    @Test
    public void compareResult1BeforeResult2() {
        when(result1.getPosition()).thenReturn(1);
        when(result2.getPosition()).thenReturn(2);
        int value = resultComparator.compare(result1,result2);
        assertEquals(value, -1);
    }

    @Test
    public void compareResult1AfterResult2() {
        when(result1.getPosition()).thenReturn(3);
        when(result2.getPosition()).thenReturn(2);
        int value = resultComparator.compare(result1,result2);
        assertEquals(value, 1);
    }

    @Test
    public void compareResult1WhenResult2() {
        when(result1.getPosition()).thenReturn(1);
        when(result2.getPosition()).thenReturn(1);
        int value = resultComparator.compare(result1,result2);
        assertEquals(value, 0);
    }
}