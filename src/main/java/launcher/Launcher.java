package launcher;

import engine.GameEngine;
import main.DungeonCrawler;
import main.ObjectFactory;
import timer.FramesPerSecondHandler;
import ui.DialogueFrame;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class Launcher {

	public static void main(String[] args) {
		ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
		DialogueFrame dialogueFrame = ObjectFactory.getDefaultDialogueFrame();
		GameFrame gameFrame = ObjectFactory.getDefaultGameFrame();
		FramesPerSecondHandler framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();

		new DungeonCrawler(threadWrapper, gameEngine, gameFrame, dialogueFrame, framesPerSecondHandler);
	}
}
