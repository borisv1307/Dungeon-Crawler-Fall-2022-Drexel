package main;

import engine.GameEngine;
import parser.LevelCreator;
import timer.FramesPerSecondHandler;
import ui.*;
import values.TunableParameters;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
	private ObjectFactory() {
	}

	private static DialogueSystem defaultDialogueSystem = new DialogueSystem();
	private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();

	private static LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
			new ReaderWrapper());

	private static GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator, defaultDialogueSystem);


	private static GamePanel defaultGamePanel = new GamePanel(defaultGameEngine, new TilePainter());

	private static GameFrame defaultGameFrame = new GameFrame(defaultGamePanel,
			new WindowAdapterSystemExit(defaultGameEngine));

	private static FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
			TunableParameters.TARGET_FPS, new SystemWrapper());

	public static ThreadWrapper getDefaultThreadWrapper() {
		return defaultThreadWrapper;
	}

	public static GameEngine getDefaultGameEngine() {
		return defaultGameEngine;
	}

	public static GameFrame getDefaultGameFrame() {
		return defaultGameFrame;
	}

	public static FramesPerSecondHandler getDefaultFramesPerSecondHandler() {
		return defaultFramesPerSecondHandler;
	}

}
