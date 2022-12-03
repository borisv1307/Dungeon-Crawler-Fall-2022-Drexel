package tiles;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import parser.LevelCreator;
import values.TileColorMap;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class TileEffectsTest {

    GameEngine gameEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine(new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
                new ReaderWrapper()));
    }

    @Test
    public void recognize_helpful_tile() {

        assertEquals(TileType.PASSABLE_HELPFUL, gameEngine.getTileFromCoordinates(3, 2));
    }

    @Test
    public void recognize_helpful_tile_color() {

        assertEquals(Color.PINK, TileColorMap.get(gameEngine.getTileFromCoordinates(3, 2)));
    }

    @Test
    public void recognize_harmful_tile() {

        assertEquals(TileType.PASSABLE_HARMFUL, gameEngine.getTileFromCoordinates(10, 2));
    }

    @Test
    public void recognize_harmful_tile_color() {

        assertEquals(Color.RED, TileColorMap.get(gameEngine.getTileFromCoordinates(10, 2)));
    }
}
