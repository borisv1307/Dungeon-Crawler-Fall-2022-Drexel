package tiles;

import engine.GameEngine;
import main.DungeonCrawler;
import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import values.TileColorMap;
import wrappers.ThreadWrapper;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TileEffectsTest {

    ThreadWrapper threadWrapper;
    GameEngine gameEngine;
    GameFrame gameFrame;
    FramesPerSecondHandler framesPerSecondHandler;

    @Before
    public void setup() {
        threadWrapper = ObjectFactory.getDefaultThreadWrapper();
        gameEngine = ObjectFactory.getDefaultGameEngine();
        gameFrame = ObjectFactory.getDefaultGameFrame();
        framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();
    }

    @Test
    public void recognize_helpful_tile() {

        new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
        assertEquals(TileType.PASSABLE_HELPFUL, gameEngine.getTileFromCoordinates(3, 2));
    }

    @Test
    public void recognize_helpful_tile_color() {

        new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
        assertEquals(Color.PINK, TileColorMap.get(gameEngine.getTileFromCoordinates(3, 2)));
    }

    @Test
    public void recognize_harmful_tile() {

        new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
        assertEquals(TileType.PASSABLE_HARMFUL, gameEngine.getTileFromCoordinates(10, 2));
    }

    @Test
    public void recognize_harmful_tile_color() {

        new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
        assertEquals(Color.RED, TileColorMap.get(gameEngine.getTileFromCoordinates(10, 2)));
    }
}
