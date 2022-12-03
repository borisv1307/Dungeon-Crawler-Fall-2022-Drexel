package portal;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import parser.LevelCreator;
import parser.LevelCreatorITHelper;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PortalTest extends LevelCreatorITHelper {
    private static final int ONE = 1;
    private static final int COORDINATE_OFFSET = ONE;

    @Before
    public void setUp() throws Throwable {
        List<String> levelStrings = new ArrayList<>();
        levelStrings.add("XXX");
        levelStrings.add("X X");
        levelStrings.add("XPX");
        levelStrings.add("XOX");
        levelStrings.add("XXX");
        writeLevelFile(levelStrings);
        gameEngine = new GameEngine(
                new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
    }

    @Test
    public void get_portal_tile() {
        TileType actual = gameEngine.getTileFromCoordinates(2 - COORDINATE_OFFSET, 4 - COORDINATE_OFFSET);
        assertThat(actual, equalTo(TileType.PORTAL));
    }

    @Test
    public void get_empty_tile() {
        TileType actual = gameEngine.getTileFromCoordinates(2 - COORDINATE_OFFSET, 2 - COORDINATE_OFFSET);
        assertThat(actual, equalTo(TileType.PASSABLE));
    }

    @Test
    public void move_player_down_to_portal() {
        TileType attemptedLocation = gameEngine.getTileFromCoordinates(
                gameEngine.getPlayerXCoordinate() + 0,
                gameEngine.getPlayerYCoordinate() + 1);
        assertThat(attemptedLocation, equalTo(TileType.PORTAL));
    }

    @Test
    public void player_transported_to_empty() {
        gameEngine.keyDown();

        TileType actual = gameEngine.getTileFromCoordinates(
                gameEngine.getPlayerXCoordinate(),
                gameEngine.getPlayerYCoordinate());
        assertThat(actual, equalTo(TileType.PASSABLE));
    }
}
