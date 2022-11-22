package launcher;

import engine.GameEngine;
import main.DungeonCrawler;
import main.ObjectFactory;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class Launcher {

	static {
		System.setProperty("java.awt.headless", "false");
		System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	}

	public static void main(String[] args) {

		ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
		GameFrame gameFrame = ObjectFactory.getDefaultGameFrame();
		FramesPerSecondHandler framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();

		new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
	}
}
