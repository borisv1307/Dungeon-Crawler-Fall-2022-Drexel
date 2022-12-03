package launcher;

import engine.GameEngine;
import engine.ProjectileHandler;
import main.DungeonCrawler;
import main.ObjectFactory;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class Launcher {

	public static void main(String[] args) {
		ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
		GameFrame gameFrame = ObjectFactory.getDefaultGameFrame();
		FramesPerSecondHandler framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();
		ProjectileHandler projectileHandler = ObjectFactory.getDefaultProjectileHandler();

		new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler, projectileHandler);
	}
}
