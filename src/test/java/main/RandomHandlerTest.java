package main;

import org.junit.Test;
import org.mockito.Mockito;
import wrappers.RandomWrapper;

import static org.junit.Assert.assertEquals;

public class RandomHandlerTest {

    @Test
    public void random_range_returns_correct_int(){
        RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
        RandomHandler random = new RandomHandler(randomWrapper);
        Mockito.when(randomWrapper.getRandomDouble()).thenReturn(0.00000);
        assertEquals(random.getRandomIntInRange(1, 20), 1);
        Mockito.when(randomWrapper.getRandomDouble()).thenReturn(0.9999);
        assertEquals(random.getRandomIntInRange(1, 20), 19);
        Mockito.when(randomWrapper.getRandomDouble()).thenReturn(0.6667);
        assertEquals(random.getRandomIntInRange(5, 20), 15);
    }

}
