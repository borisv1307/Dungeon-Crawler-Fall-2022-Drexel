package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class EnemyMovementStepDefs extends LevelCreationStepDefHelper {

    private GameEngine gameEngine;

    @Given("^the level design with enemy focus is:$")
    public void level_is(List<String> levelStrings) throws Throwable {
        writeLevelFile(levelStrings);
        gameEngine = new GameEngine(
                new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
    }

    @When("^the enemy moves left$")
    public void the_enemy_moves_left() throws Throwable {
        gameEngine.enemyMovement(-1, 0);
    }

    @When("^the enemy moves right$")
    public void the_enemy_moves_right() throws Throwable {
        gameEngine.enemyMovement(1, 0);
    }

    @When("^the enemy moves up$")
    public void the_enemy_moves_up() throws Throwable {
        gameEngine.enemyMovement(0, -1);
    }

    @When("^the enemy moves down$")
    public void the_enemy_moves_down() throws Throwable {
        gameEngine.enemyMovement(0, 1);
    }

    @When("^the player tries to move right$")
    public void the_player_moves_right() throws Throwable {
        gameEngine.keyRight();
    }

    @When("^the player tries to move up$")
    public void the_player_moves_up() throws Throwable {
        gameEngine.keyUp();
    }

    @Then("^the enemy is located at \\((\\d+), (\\d+)\\)$")
    public void the_enemy_is_located_at(int enemyX, int enemyY) throws Throwable {
        assertThat(gameEngine.getEnemyXCoordinate(), equalTo(enemyX - COORDINATE_OFFSET));
        assertThat(gameEngine.getEnemyYCoordinate(), equalTo(enemyY - COORDINATE_OFFSET));
    }

    @Then("^the enemy is not located at \\((\\d+), (\\d+)\\)$")
    public void the_enemy_is_not_located_at(int enemyX, int enemyY) throws Throwable {
        assertThat(gameEngine.getEnemyYCoordinate(), not(enemyY - COORDINATE_OFFSET));
    }
}
