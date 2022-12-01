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


    @When("^the player moves in a circle after moving down")
    public void the_player_moves_down_then_in_a_circle() throws Throwable {
        gameEngine.keyDown();
        gameEngine.keyLeft();
        gameEngine.keyUp();
        gameEngine.keyUp();
        gameEngine.keyRight();
        gameEngine.keyRight();
        gameEngine.keyDown();
        gameEngine.keyDown();
        gameEngine.keyLeft();
    }

    @When("^the player moves down, then around")
    public void the_player_moves_down_then_around() throws Throwable {
        gameEngine.keyDown();
        gameEngine.keyLeft();
        gameEngine.keyUp();
        gameEngine.keyUp();
        gameEngine.keyRight();
        gameEngine.keyRight();
        gameEngine.keyDown();
        gameEngine.keyDown();
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

    @Then("^the player is located at \\((\\d+), (\\d+)\\)$")
    public void the_player_is_located_at(int playerX, int playerY) throws Throwable {
        assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX - COORDINATE_OFFSET));
        assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY - COORDINATE_OFFSET));
    }

    @Then("^the player's HP is (\\d+)$")
    public void the_player_health_is(int playerHP) throws Throwable {
        assertThat(gameEngine.getPlayerHP(), equalTo(playerHP));
    }


    @Then("^the player is on a (.+) tile$")
    public void the_player_is_on(String currentTile) throws Throwable {
        assertThat(gameEngine.getTileFromCoordinates(gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate()),
                equalTo(TileType.valueOf(currentTile)));
    }

    @Then("^the player has regen (.+)$")
    public void the_player_regen_status(String regenStatus) throws Throwable {
        assertThat(gameEngine.getPlayerRegenStatus(), equalTo(regenStatus.equalsIgnoreCase("on")));
    }

    @Then("^the regen counter is (\\d+)$")
    public void the_player_regen_counter_is(int regenCounter) {
        assertThat(gameEngine.getPlayerRegenCounter(), equalTo(regenCounter));
    }

    @Then("^the player has drain (.+)$")
    public void the_player_drain_status(String drainStatus) throws Throwable {
        assertThat(gameEngine.getPlayerDrainStatus(), equalTo(drainStatus.equalsIgnoreCase("on")));
    }

    @Then("^the drain counter is (\\d+)$")
    public void the_player_drain_counter_is(int drainCounter) {
        assertThat(gameEngine.getPlayerDrainCounter(), equalTo(drainCounter));
    }

}
