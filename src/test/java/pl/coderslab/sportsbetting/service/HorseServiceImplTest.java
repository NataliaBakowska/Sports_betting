package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.Horse;
import pl.coderslab.sportsbetting.repository.HorseRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HorseServiceImplTest {

    private HorseServiceImpl horseService;

    @Mock
    private HorseRepository horseRepository;

    @Before
    public void setUp() throws Exception {
        horseService = new HorseServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(horseService,"horseRepository", horseRepository);
    }

    @Test
    public void saveHorse() {
        horseService.saveHorse(new Horse());
        verify(horseRepository,times(1)).save(any(Horse.class));
    }

    @Test
    public void findHorseById() {
        horseService.findHorseById(1L);
        verify(horseRepository,times(1)).findHorseById(1L);
    }
}