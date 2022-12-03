package timer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import wrappers.SystemWrapper;

public class FramesPerSecondHandlerTest {

	private final int DESIRED_FRAMES_PER_SECOND = 30;
	private final long FRAME_PERIOD_NS = 33333333;
	FramesPerSecondHandler framesPerSecondHandler;
	SystemWrapper systemWrapper;

	@Before
	public void setUp() {
		systemWrapper = Mockito.mock(SystemWrapper.class);
		framesPerSecondHandler = new FramesPerSecondHandler(DESIRED_FRAMES_PER_SECOND, systemWrapper);
	}

	@Test
	public void enough_time_has_elapsed() {
		Mockito.when(systemWrapper.nanoTime()).thenReturn(FRAME_PERIOD_NS);
		boolean actual = framesPerSecondHandler.hasEnoughTimeElapsed();

		assertTrue(actual);
	}

	@Test
	public void enough_time_has_not_elapsed() {
		Mockito.when(systemWrapper.nanoTime()).thenReturn(FRAME_PERIOD_NS - 1);
		boolean actual = framesPerSecondHandler.hasEnoughTimeElapsed();

		assertFalse(actual);
	}

	@Test
	public void enough_time_has_elapsed_twice() {
		Mockito.when(systemWrapper.nanoTime()).thenReturn(FRAME_PERIOD_NS, FRAME_PERIOD_NS * 2);
		framesPerSecondHandler.hasEnoughTimeElapsed();
		framesPerSecondHandler.resetLastRunTimer();

		boolean actual = framesPerSecondHandler.hasEnoughTimeElapsed();

		assertTrue(actual);
	}

	@Test
	public void calculate_sleep_duration() {
		Mockito.when(systemWrapper.nanoTime()).thenReturn(1 * (long) FramesPerSecondHandler.MICROSECONDS_IN_A_SECOND);

		long actual = framesPerSecondHandler.calculateSleepDurationInMilliSeconds();

		long expected = FRAME_PERIOD_NS / FramesPerSecondHandler.MICROSECONDS_IN_A_SECOND;
		expected -= 1 + FramesPerSecondHandler.THREAD_SLEEP_LAG_DELAY;
		assertEquals(expected, actual);

	}

	@Test
	public void calculate_sleep_duration_zero() {
		Mockito.when(systemWrapper.nanoTime()).thenReturn((33 - FramesPerSecondHandler.THREAD_SLEEP_LAG_DELAY)
				* (long) FramesPerSecondHandler.MICROSECONDS_IN_A_SECOND);

		long actual = framesPerSecondHandler.calculateSleepDurationInMilliSeconds();

		assertEquals(0, actual);

	}

}
