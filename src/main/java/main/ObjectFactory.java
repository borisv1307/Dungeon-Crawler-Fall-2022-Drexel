package main;

import javax.swing.*;

import engine.GameEngine;
import parser.LevelCreator;
import timer.FramesPerSecondHandler;
import ui.*;
import values.TunableParameters;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
	private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();
	private static LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
			new ReaderWrapper());

	private static ScorePanel defaultScorePanel = new ScorePanel(new JPanel());
	private static GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator, defaultScorePanel);
	private static GameFrame defaultGameFrame = new GameFrame(new GamePanel(defaultGameEngine, new TilePainter()),
			new WindowAdapterSystemExit(defaultGameEngine), defaultScorePanel);
	private static FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
			TunableParameters.TARGET_FPS, new SystemWrapper());

	private ObjectFactory() {
	}

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

	public static ScorePanel getDefaultScorePanel() {
		return defaultScorePanel;
	}
}
