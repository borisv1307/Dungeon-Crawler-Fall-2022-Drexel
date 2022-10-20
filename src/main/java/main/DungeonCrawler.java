package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import engine.GameEngine;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class DungeonCrawler implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(DungeonCrawler.class.getName());

	private final ThreadWrapper threadWrapper;
	private final GameEngine gameEngine;
	private final GameFrame gameFrame;
	private final FramesPerSecondHandler framesPerSecondHandler;

	public DungeonCrawler(ThreadWrapper threadWrapper, GameEngine gameEngine, GameFrame gameFrame,
			FramesPerSecondHandler framesPerSecondHandler) {
		this.threadWrapper = threadWrapper;
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
		this.framesPerSecondHandler = framesPerSecondHandler;
		this.threadWrapper.createNewThreadWithDungeonCrawler(this);
	}

	@Override
	public void run() {
		while (!gameEngine.isExit()) {
			try {
				runIfEnoughTimeHasElapsed();
			} catch (InterruptedException e) {
				threadWrapper.currentThreadInterrupt();
				LOGGER.log(Level.SEVERE, e.toString(), e);
				gameEngine.setExit(true);
			}
			terminateIfExit();
		}
		terminate();
	}

	private void terminateIfExit() {
		if (gameEngine.isExit()) {
			terminate();
		}
	}

	public void terminate() {
		gameFrame.dispose();
	}

	private void runIfEnoughTimeHasElapsed() throws InterruptedException {
		if (framesPerSecondHandler.hasEnoughTimeElapsed()) {
			framesPerSecondHandler.resetLastRunTimer();
			gameEngine.run(gameFrame);
			threadWrapper.sleep(framesPerSecondHandler.calculateSleepDurationInMilliSeconds());
		}
	}
}
