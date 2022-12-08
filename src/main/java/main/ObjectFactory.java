package main;

import engine.GameEngine;
import parser.LevelCreator;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import ui.WindowAdapterSystemExit;
import values.TunableParameters;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
    private static final ThreadWrapper defaultThreadWrapper = new ThreadWrapper();
    private static final LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
            new ReaderWrapper());
    private static final GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator);
    private static final GameFrame defaultGameFrame = new GameFrame(new GamePanel(defaultGameEngine, new TilePainter()),
            new WindowAdapterSystemExit(defaultGameEngine));
    private static final FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
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
