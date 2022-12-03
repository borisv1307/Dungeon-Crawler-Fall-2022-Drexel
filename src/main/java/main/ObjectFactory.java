package main;

import engine.CombatEngine;
import engine.EnemySpawner;
import engine.GameEngine;
import parser.LevelCreator;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import ui.WindowAdapterSystemExit;
import values.TunableParameters;
import wrappers.RandomizerWrapper;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
    private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();
    private static LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
            new ReaderWrapper());
    private static FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
            TunableParameters.TARGET_FPS, new SystemWrapper());
    private static GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator);
    private static GameFrame defaultGameFrame = new GameFrame(new GamePanel(defaultGameEngine, new TilePainter()),
            new WindowAdapterSystemExit(defaultGameEngine));
    private static SystemWrapper defaultSystemWrapper = new SystemWrapper();
    private static RandomizerWrapper defaultRandomizerWrapper = new RandomizerWrapper(defaultSystemWrapper);
    private static EnemySpawner defaultEnemySpawner = new EnemySpawner(defaultRandomizerWrapper, defaultGameEngine);
    private static CombatEngine defaultCombatEngine = new CombatEngine(defaultEnemySpawner);


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

    public static CombatEngine getDefaultCombatEngine() {
        return defaultCombatEngine;
    }

}
