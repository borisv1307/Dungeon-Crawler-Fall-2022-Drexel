package main;

import board.piece.BoardPiece;
import board.piece.BoardPieceFactory;
import board.piece.Empty;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import enums.Direction;
import enums.TileType;
import parser.FileLevelCreator;
import parser.LevelCreationStepDefHelper;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class MovementStepDefs extends LevelCreationStepDefHelper {

    private GameEngine gameEngine;

    @Given("^the level (\\d+) design is:$")
    public void the_level_design_is(int level, List<String> levelStrings) throws FileNotFoundException, UnsupportedEncodingException {
        writeLevelFile(level, levelStrings);
        gameEngine = new GameEngine(
                new FileLevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper(), new BoardPieceFactory()));
    }

    @Then("^the goal is located at \\((\\d+), (\\d+)\\)$")
    public void the_goal_is_located_at(int goalX, int goalY) {
        verifyTileTypeLocation(TileType.GOAL, goalX, goalY);
    }

    @When("^the enemy moves left$")
    public void the_enemy_moves_left() {
        gameEngine.movement(TileType.ENEMY, Direction.LEFT);
    }

    @When("^the enemy moves right$")
    public void the_enemy_moves_right() {
        gameEngine.movement(TileType.ENEMY, Direction.RIGHT);
    }

    @When("^the enemy moves up$")
    public void the_enemy_moves_up() {
        gameEngine.movement(TileType.ENEMY, Direction.UP);
    }

    @When("^the enemy moves down$")
    public void the_enemy_moves_down() {
        gameEngine.movement(TileType.ENEMY, Direction.DOWN);
    }

    @When("^the player moves left$")
    public void the_player_moves_left() {
        gameEngine.movement(TileType.PLAYER, Direction.LEFT);
    }

    @When("^the player moves right$")
    public void the_player_moves_right() {
        gameEngine.movement(TileType.PLAYER, Direction.RIGHT);
    }

    @When("^the player moves up$")
    public void the_player_moves_up() {
        gameEngine.movement(TileType.PLAYER, Direction.UP);
    }

    @When("^the player moves down$")
    public void the_player_moves_down() {
        gameEngine.movement(TileType.PLAYER, Direction.DOWN);
    }

    @When("^the enemy moves$")
    public void the_enemy_moves() {
        gameEngine.moveEnemy();
    }

    @Then("^the player is located at \\((\\d+), (\\d+)\\)$")
    public void the_player_is_located_at(int playerX, int playerY) {
        verifyTileTypeLocation(TileType.PLAYER, playerX, playerY);
    }

    @Then("^the enemy is located at \\((\\d+), (\\d+)\\)$")
    public void the_enemy_is_located_at(int enemyX, int enemyY) {

        verifyTileTypeLocation(TileType.ENEMY, enemyX, enemyY);
    }

    @Then("^level (\\d+) is generated$")
    public void level_is_generated(int level) {
        assertThat(gameEngine.getLevel(), equalTo(level));
    }

    @Then("^win count is (\\d+)$")
    public void win_count_is(int wins) {
        assertThat(gameEngine.getWinCount(), equalTo(wins));
    }

    @Then("^lose count is (\\d+)$")
    public void lose_count_is(int losses) {
        assertThat(gameEngine.getLossCount(), equalTo(losses));
    }

    @Then("^space \\((\\d+), (\\d+)\\) is Empty$")
    public void space_is_Empty(int xCoordinate, int yCoordinate) {
        BoardPiece boardPiece = gameEngine.getGameBoard().getBoardPieceFromCoordinates(xCoordinate - COORDINATE_OFFSET, yCoordinate - COORDINATE_OFFSET);
        assertEquals(Empty.class, boardPiece.getClass());
    }

    private void verifyTileTypeLocation(final TileType expectedTileType, final int xCoordinate, final int yCoordinate) {
        TileType tileType = gameEngine.getGameBoard().getBoardPieceFromCoordinates(xCoordinate - COORDINATE_OFFSET, yCoordinate - COORDINATE_OFFSET).getTileType();
        assertThat(tileType, equalTo(expectedTileType));
    }
}
