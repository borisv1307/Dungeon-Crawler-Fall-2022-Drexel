package launcher;

import engine.GameEngine;
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
        gameEngine.setCombatEngine(ObjectFactory.getDefaultCombatEngine());

        new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
    }
}
