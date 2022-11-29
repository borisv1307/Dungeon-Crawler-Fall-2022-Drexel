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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MovementStepDefs extends LevelCreationStepDefHelper {

    private GameEngine gameEngine;

    @Given("^the level design is:$")
    public void level_is(List<String> levelStrings) throws Throwable {
        writeLevelFile(levelStrings);
        gameEngine = new GameEngine(
                new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
    }

    @When("^the player moves left$")
    public void the_player_moves_left() throws Throwable {
        gameEngine.keyLeft();
    }

    @When("^the player moves right$")
    public void the_player_moves_right() throws Throwable {
        gameEngine.keyRight();
    }

    @When("^the player moves up$")
    public void the_player_moves_up() throws Throwable {
        gameEngine.keyUp();
    }

    @When("^the player moves down$")
    public void the_player_moves_down() throws Throwable {
        gameEngine.keyDown();
    }

    @Then("^the player is located at \\((\\d+), (\\d+)\\)$")
    public void the_player_is_located_at(int playerX, int playerY) throws Throwable {
        assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX - COORDINATE_OFFSET));
        assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY - COORDINATE_OFFSET));
    }

    @Then("^the player's HP is (\\d+)$")
    public void the_player_health_is(int playerHP) throws Throwable {
        assertThat(gameEngine.getPlayerHP(), equalTo(playerHP));
    }

    @When("^the player moves down and left$")
    public void the_player_moves_down_and_left() throws Throwable {
        gameEngine.keyDown();
        gameEngine.keyLeft();
    }

    @When("^the player moves in a circle$")
    public void the_player_moves_in_a_circle() throws Throwable {
        gameEngine.keyUp();
        gameEngine.keyRight();
        gameEngine.keyDown();
        gameEngine.keyDown();
        gameEngine.keyLeft();
        gameEngine.keyLeft();
        gameEngine.keyUp();
        gameEngine.keyUp();
        gameEngine.keyRight();
    }

}
