package main;

import engine.GameEngine;
import timer.FramesPerSecondHandler;
import ui.DialogueFrame;
import ui.GameFrame;
import wrappers.ThreadWrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DungeonCrawler implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(DungeonCrawler.class.getName());

	private final ThreadWrapper threadWrapper;
	private final GameEngine gameEngine;
	private final GameFrame gameFrame;
	private final DialogueFrame dialogueFrame;
	private final FramesPerSecondHandler framesPerSecondHandler;


	public DungeonCrawler(ThreadWrapper threadWrapper, GameEngine gameEngine, GameFrame gameFrame,
						  DialogueFrame dialogueFrame, FramesPerSecondHandler framesPerSecondHandler) {
		this.threadWrapper = threadWrapper;
		this.gameEngine = gameEngine;
		this.gameFrame = gameFrame;
		this.dialogueFrame = dialogueFrame;
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
		dialogueFrame.dispose();
	}

	private void runIfEnoughTimeHasElapsed() throws InterruptedException {
		if (framesPerSecondHandler.hasEnoughTimeElapsed()) {
			framesPerSecondHandler.resetLastRunTimer();
			gameEngine.run(gameFrame);
			gameEngine.run(dialogueFrame);
			threadWrapper.sleep(framesPerSecondHandler.calculateSleepDurationInMilliSeconds());
		}
	}
}
