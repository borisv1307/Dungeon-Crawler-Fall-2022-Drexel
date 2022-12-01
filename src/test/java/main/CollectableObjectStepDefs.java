package main;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import tiles.TileType;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CollectableObjectStepDefs extends LevelCreationStepDefHelper {

    GameEngine gameEngine;


    @When("^a tile is passable and randomly selected$")
    public void a_tile_is_passable_and_randomly_selected() {
        ArrayList<Point> allPassableTiles = new ArrayList<>();
        allPassableTiles.add(new Point(1, 1));
        gameEngine.getRandomPassableTile(allPassableTiles);
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
        assertEquals(TileType.OBJECT, gameEngine.getTileFromCoordinates(xCoordinate, yCoordinate));
    }
}
