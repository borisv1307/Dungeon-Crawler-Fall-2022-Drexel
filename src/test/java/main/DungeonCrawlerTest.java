package main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import engine.GameEngine;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class DungeonCrawlerTest {

	private static final long SLEEP_TIME = 100;
	private ThreadWrapper threadWrapper;
	private GameEngine gameEngine;
	private GameFrame gameFrame;
	private FramesPerSecondHandler framesPerSecondHandler;

	private DungeonCrawler dungeonCrawler;

	@Before
	public void setUp() {
		threadWrapper = Mockito.mock(ThreadWrapper.class);
		gameEngine = Mockito.mock(GameEngine.class);
		gameFrame = Mockito.mock(GameFrame.class);
		framesPerSecondHandler = Mockito.mock(FramesPerSecondHandler.class);
		dungeonCrawler = new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
		Mockito.when(gameEngine.isExit()).thenReturn(false, true);
		Mockito.when(framesPerSecondHandler.hasEnoughTimeElapsed()).thenReturn(true);
		Mockito.when(framesPerSecondHandler.calculateSleepDurationInMilliSeconds()).thenReturn(SLEEP_TIME);
	}

	@Test
	public void terminate_if_exit_is_true() {
		Mockito.when(gameEngine.isExit()).thenReturn(true);
		dungeonCrawler.run();
		Mockito.verify(gameFrame).dispose();
	}

	@Test
	public void do_not_run_if_not_enough_time_has_elapsed() {
		Mockito.when(framesPerSecondHandler.hasEnoughTimeElapsed()).thenReturn(false);
		dungeonCrawler.run();
		Mockito.verify(gameEngine, Mockito.never()).run(gameFrame);
	}

	@Test
	public void set_exit_when_thread_interrupted_exception() throws InterruptedException {
		InterruptedException interruptedException = Mockito.mock(InterruptedException.class);
		Mockito.doThrow(interruptedException).when(threadWrapper).sleep(SLEEP_TIME);
		dungeonCrawler.run();
		Mockito.verify(threadWrapper).currentThreadInterrupt();
		Mockito.verify(gameEngine).setExit(true);
	}

	@Test
	public void run_and_exit_only_when_set_exit_true() throws InterruptedException {
		Mockito.when(gameEngine.isExit()).thenReturn(false, false, true);
		dungeonCrawler.run();
		Mockito.verify(framesPerSecondHandler).resetLastRunTimer();
		Mockito.verify(gameEngine).run(gameFrame);
		Mockito.verify(threadWrapper).sleep(SLEEP_TIME);
	}
}
