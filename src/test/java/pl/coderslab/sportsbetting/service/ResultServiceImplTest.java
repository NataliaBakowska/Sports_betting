package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.Result;
import pl.coderslab.sportsbetting.repository.ResultRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

    private ResultServiceImpl resultService;

    @Mock
    private ResultRepository resultRepository;

    @Before
    public void setUp() throws Exception {
        resultService = new ResultServiceImpl();
        ReflectionTestUtils.setField(resultService,"resultRepository", resultRepository);

    }

    @Test
    public void saveResult() {
        resultService.saveResult(new Result());
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    public void findResultsWithGameId() {
        resultService.findResultsWithGameId(1L);
        verify(resultRepository,times(1)).findAllByGame_Id(1L);
    }

    @Test
    public void findByGameIdAndHorseId() {
        when(resultRepository.findByGame_IdAndHorse_Id(10L, 20L)).thenReturn(new Result());
        Result result = resultService.findByGameIdAndHorseId(10L,20L);
        assertNotNull(result);
    }
}