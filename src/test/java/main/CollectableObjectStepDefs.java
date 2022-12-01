package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.RandomWrapper;
import wrappers.ReaderWrapper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CollectableObjectStepDefs extends LevelCreationStepDefHelper {

    GameEngine gameEngine;
    RandomWrapper randomWrapper;


    @Given("^the design for the level is:$")
    public void level_is(List<String> levelStrings) throws Throwable {
        writeLevelFile(levelStrings);
        gameEngine = new GameEngine(
                new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
        randomWrapper = mock(RandomWrapper.class);
    }

    @When("^a tile is passable and randomly selected$")
    public void a_tile_is_passable_and_randomly_selected() {
        ArrayList<Point> allPassableTiles = new ArrayList<>();
        Point point = new Point(1, 1);
        allPassableTiles.add(point);
        when(randomWrapper.getRandomPassableTile(allPassableTiles)).thenReturn(point);
    }

    @Then("^an object will be placed on that tile$")
    public void an_object_will_be_placed_on_that_tile() {
        Point pointToUpdate = mock(Point.class);
        gameEngine.addObjectToTile(pointToUpdate);
    }

    @When("^the game has been active for (\\d+) seconds$")
    public void theGameHasBeenActiveForSeconds(int numberOfSeconds) {
        gameEngine.activateGameTimer(numberOfSeconds);
    }


    @Then("^there will be objects available within the level$")
    public void thereWillBeObjectsAvailableWithinTheLevel() {

    }

    @When("^\\((\\d+),(\\d+)\\) is selected as the randomly passable tile$")
    public void isSelectedAsTheRandomlyPassableTile(int xCoordinate, int yCoordinate) {
        Point point = new Point(xCoordinate, yCoordinate);
        gameEngine.addObjectToTile(point);
    }

    @Then("^an object will be located at \\((\\d+),(\\d+)\\)$")
    public void anObjectWillBeLocatedAt(int xCoordinate, int yCoordinate) {
        TileType actual = gameEngine.getTileFromCoordinates(xCoordinate, yCoordinate);
        assertEquals(TileType.OBJECT, actual);
    }
}
