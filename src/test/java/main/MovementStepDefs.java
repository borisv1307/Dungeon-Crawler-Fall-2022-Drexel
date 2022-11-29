package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.FileLevelCreator;
import parser.LevelCreationStepDefHelper;
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
                new FileLevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
    }

    @When("^the enemy moves left$")
    public void the_enemy_moves_left() {
        gameEngine.keyLeft(TileType.ENEMY);
    }

    @When("^the enemy moves right$")
    public void the_enemy_moves_right() {
        gameEngine.keyRight(TileType.ENEMY);
    }

    @When("^the enemy moves up$")
    public void the_enemy_moves_up() {
        gameEngine.keyUp(TileType.ENEMY);
    }

    @When("^the enemy moves down$")
    public void the_enemy_moves_down() {
        gameEngine.keyDown(TileType.ENEMY);
    }

    @When("^the player moves left$")
    public void the_player_moves_left() {
        gameEngine.keyLeft(TileType.PLAYER);
    }

    @When("^the player moves right$")
    public void the_player_moves_right() {
        gameEngine.keyRight(TileType.PLAYER);
    }

    @When("^the player moves up$")
    public void the_player_moves_up() {
        gameEngine.keyUp(TileType.PLAYER);
    }

    @When("^the player moves down$")
    public void the_player_moves_down() {
        gameEngine.keyDown(TileType.PLAYER);
    }

    @Then("^the player is located at \\((\\d+), (\\d+)\\)$")
    public void the_player_is_located_at(int playerX, int playerY) {
        assertThat(gameEngine.getXCoordinate(TileType.PLAYER), equalTo(playerX - COORDINATE_OFFSET));
        assertThat(gameEngine.getYCoordinate(TileType.PLAYER), equalTo(playerY - COORDINATE_OFFSET));
    }

    @Then("^the enemy is located at \\((\\d+), (\\d+)\\)$")
    public void the_enemy_is_located_at(int enemyX, int enemyY) {
        assertThat(gameEngine.getXCoordinate(TileType.ENEMY), equalTo(enemyX - COORDINATE_OFFSET));
        assertThat(gameEngine.getYCoordinate(TileType.ENEMY), equalTo(enemyY - COORDINATE_OFFSET));
    }
}
