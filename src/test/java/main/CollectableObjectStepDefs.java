package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

import java.util.List;

public class CollectableObjectStepDefs extends LevelCreationStepDefHelper {

    private GameEngine gameEngine;

    //TODO
    @Given("^the design for the level is:$")
    public void level_is(List<String> levelStrings) throws Throwable {
        writeLevelFile(levelStrings);
        gameEngine = new GameEngine(
                new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
        gameEngine.initializeRandomObjects();
    }

    @When("^a tile is empty and there is an object available$")
    public void a_tile_is_empty_and_there_is_an_object_available() {
        gameEngine.getRandomPassableTile();
        gameEngine.getCollectableObject();
    }

    @Then("^an object will be placed on that tile$")
    public void an_object_will_be_placed_on_that_tile() {
        gameEngine.addObjectToTile();
    }

    @When("^two tiles are empty and there are two objects available$")
    public void twoTilesAreEmptyAndThereAreTwoObjectsAvailable() {
        gameEngine.getRandomPassableTile();
        gameEngine.getCollectableObject();
        gameEngine.getRandomPassableTile();
        gameEngine.getCollectableObject();
    }

    @Then("^one object will be placed on each tile$")
    public void oneObjectWillBePlacedOnEachTile() {
        gameEngine.addObjectToTile();
        gameEngine.addObjectToTile();
    }

    @When("^the game has been active for (\\d+) seconds$")
    public void theGameHasBeenActiveForSeconds(int numberOfSeconds) throws InterruptedException {
        gameEngine.activateGameTimer(numberOfSeconds);
    }

    @Then("^an object will be placed on a passable tile$")
    public void anObjectWillBePlacedOnAPassableTile() {
        gameEngine.addObjectToTile();
    }
}
