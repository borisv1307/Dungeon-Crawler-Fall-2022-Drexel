package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PortalStepDefs extends LevelCreationStepDefHelper {
    private static final int ONE = 1;
    private static final int COORDINATE_OFFSET = ONE;
    private GameEngine gameEngine;

    @Given("^the portal level design is:$")
    public void level_is(List<String> levelStrings) throws Throwable {
        writeLevelFile(levelStrings);
        gameEngine = new GameEngine(
                new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
    }

    @When("^the portal is located at \\((\\d+), (\\d+)\\)$")
    public void the_portal_is_located_at(int x, int y) throws Throwable {
        assertEquals(TileType.PORTAL, gameEngine.getTileFromCoordinates(x - COORDINATE_OFFSET, y - COORDINATE_OFFSET));
    }

    @When("^the empty space is located at \\((\\d+), (\\d+)\\)$")
    public void the_empty_space_is_located_at(int x, int y) throws Throwable {
        assertEquals(TileType.PASSABLE, gameEngine.getTileFromCoordinates(x - COORDINATE_OFFSET, y - COORDINATE_OFFSET));
    }

    @When("^the player moves down to portal$")
    public void the_player_moves_down_to_portal() throws Throwable {
        gameEngine.keyDown();
    }

    @Then("^the player is transported to \\((\\d+), (\\d+)\\)$")
    public void the_player_is_transported_to(int x, int y) throws Throwable {
        assertEquals(x - COORDINATE_OFFSET, gameEngine.getPlayerXCoordinate());
        assertEquals(y - COORDINATE_OFFSET, gameEngine.getPlayerYCoordinate());
    }
}
