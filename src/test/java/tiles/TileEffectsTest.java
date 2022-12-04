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
    public void inflict_Harm() {
        gameEngine.addTile(2, 2, TileType.PASSABLE_HARMFUL);
        gameEngine.addTile(3, 2, TileType.PLAYER);
        gameEngine.keyLeft();
        assertEquals(9, gameEngine.getPlayerHitpoints());
    }

    @Test
    public void harm_then_heal() {
        gameEngine.addTile(1, 2, TileType.PASSABLE_HELPFUL);
        gameEngine.addTile(2, 2, TileType.PASSABLE_HARMFUL);
        gameEngine.addTile(3, 2, TileType.PLAYER);
        gameEngine.keyLeft();
        gameEngine.keyLeft();
        assertEquals(10, gameEngine.getPlayerHitpoints());
    }

    @Test
    public void recognize_helpful_hidden_tile() {

        assertEquals(TileType.PASSABLE_HELPFUL_HIDDEN, gameEngine.getTileFromCoordinates(3, 2));
    }

    @Test
    public void recognize_helpful_tile_color() {
        gameEngine.addTile(0, 1, TileType.PASSABLE_HELPFUL);
        assertEquals(Color.PINK, TileColorMap.get(gameEngine.getTileFromCoordinates(0, 1)));
    }

    @Test
    public void recognize_harmful_hidden_tile() {

        assertEquals(TileType.PASSABLE_HARMFUL_HIDDEN, gameEngine.getTileFromCoordinates(10, 2));
    }

    @Test
    public void recognize_harmful_tile_color() {
        gameEngine.addTile(0, 1, TileType.PASSABLE_HARMFUL);
        assertEquals(Color.RED, TileColorMap.get(gameEngine.getTileFromCoordinates(0, 1)));
    }

    @Test
    public void reveal_hidden_tile_east() {
        gameEngine.revealAdjacentTiles(3, 2);
        assertEquals(Color.white, TileColorMap.get(gameEngine.getTileFromCoordinates(2, 2)));
    }

    @Test
    public void reveal_hidden_tile_west() {
        gameEngine.revealAdjacentTiles(1, 2);
        assertEquals(Color.white, TileColorMap.get(gameEngine.getTileFromCoordinates(2, 2)));
    }

    @Test
    public void reveal_hidden_tile_north() {
        gameEngine.revealAdjacentTiles(2, 3);
        assertEquals(Color.white, TileColorMap.get(gameEngine.getTileFromCoordinates(2, 2)));
    }

    @Test
    public void reveal_hidden_tile_south() {
        gameEngine.revealAdjacentTiles(2, 1);
        assertEquals(Color.white, TileColorMap.get(gameEngine.getTileFromCoordinates(2, 2)));
    }
}
