package main;

import boardPiece.BoardPieceFactory;
import engine.GameEngine;
import parser.FileLevelCreator;
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
    private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();
    private static LevelCreator defaultLevelCreator = new FileLevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
            new ReaderWrapper(), new BoardPieceFactory());
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
