package main;

import board.piece.BoardPieceFactory;
import engine.GameEngine;
import parser.LevelCreator;
import parser.RandomLevelCreator;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import ui.WindowAdapterSystemExit;
import values.TunableParameters;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

import java.security.SecureRandom;

public abstract class ObjectFactory {
    private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();
    private static LevelCreator defaultLevelCreator = new RandomLevelCreator(new BoardPieceFactory(), new SecureRandom(), 5, 20, 20);
    private static GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator);
    private static GameFrame defaultGameFrame = new GameFrame(new GamePanel(defaultGameEngine, new TilePainter()),
            new WindowAdapterSystemExit(defaultGameEngine));
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

}
