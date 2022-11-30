package parser;

import boardPiece.BoardPieceFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import engine.GameEngine;
import enums.TileType;
import org.mockito.Mockito;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;

@StepDefAnnotation
public class LevelCreatorStepDefs extends LevelCreationStepDefHelper {
    private static final int ONE = 1;
    private static final int COORDINATE_OFFSET = ONE;
    ReaderWrapper readerWrapper;
    IOException ioException;
    private GameEngine gameEngine;
    private String exceptionMessage;
    private int seed;
    private Point originalEnemyLocation;
    private Point originalPlayerLocation;
    private Point originalGoalLocation;

    @Given("^level is:$")
    public void level_is(List<String> levelStrings) throws Throwable {
        writeLevelFile(1, levelStrings);
    }

    @Given("^I have a seed of (\\d+)$")
    public void i_have_a_seed_of(int seed) throws Throwable {
        this.seed = seed;
    }

    @When("^I randomly generate level 1 with an x of (\\d+) and a y of (\\d+)$")
    public void i_randomly_generate_a_level_with_obstacles_an_x_of_and_a_y_of(int x, int y) throws Throwable {
        Random random = new Random();
        random.setSeed(seed);
        RandomLevelCreator levelCreator = new RandomLevelCreator(new BoardPieceFactory(), random, seed, x, y);

        try {
            gameEngine = new GameEngine(levelCreator);
        } catch (IllegalArgumentException e) {
            exceptionMessage = e.getMessage();
        }
    }

    @When("^I create the level$")
    public void i_create_the_level() throws Throwable {
        LevelCreator levelCreator = new FileLevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX,
                new ReaderWrapper(), new BoardPieceFactory());
        try {
            gameEngine = new GameEngine(levelCreator);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
    }

    @When("^I create the level with malfunctioning reader$")
    public void i_create_the_level_with_malfunctioning_reader() throws Throwable {
        ioException = Mockito.mock(IOException.class);
        readerWrapper = Mockito.mock(ReaderWrapper.class);
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(readerWrapper.createBufferedReader(Mockito.anyString())).thenReturn(bufferedReader);
        Mockito.doThrow(ioException).when(bufferedReader).readLine();
        LevelCreator levelCreator = new FileLevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, readerWrapper, new BoardPieceFactory());
        gameEngine = new GameEngine(levelCreator);
    }

    @When("^I randomly generate level (\\d+)$")
    public void i_randomly_generate_level(int level) {
        originalEnemyLocation = new Point(gameEngine.getXCoordinate(TileType.ENEMY), gameEngine.getYCoordinate(TileType.ENEMY));
        originalPlayerLocation = new Point(gameEngine.getXCoordinate(TileType.PLAYER), gameEngine.getYCoordinate(TileType.PLAYER));
        originalGoalLocation = new Point(gameEngine.getXCoordinate(TileType.GOAL), gameEngine.getYCoordinate(TileType.GOAL));
        gameEngine.regenerateLevel(level);
    }

    @Then("^the enemy locations are different$")
    public void the_enemy_locations_are_different() {
        assertPointsAreNotEqual(originalEnemyLocation, TileType.ENEMY);
    }

    @Then("^the goal locations are different$")
    public void the_goal_locations_are_different() {
        assertPointsAreNotEqual(originalGoalLocation, TileType.GOAL);
    }

    @Then("^the player locations are different$")
    public void the_player_locations_are_different() {
        assertPointsAreNotEqual(originalPlayerLocation, TileType.PLAYER);
    }

    @Then("^the enemy locations are the same$")
    public void the_enemy_locations_are_the_same() throws Throwable {
        assertPointsAreEqual(originalEnemyLocation, TileType.ENEMY);
    }

    @Then("^the goal locations are the same$")
    public void the_goal_locations_are_the_same() throws Throwable {
        assertPointsAreEqual(originalGoalLocation, TileType.GOAL);
    }

    @Then("^the player locations are the same$")
    public void the_player_locations_are_the_same() throws Throwable {
        assertPointsAreEqual(originalPlayerLocation, TileType.PLAYER);
    }

    @Then("^starting from the top-left:$")
    public void starting_from_the_top_left() {
    }

    @Then("^the player's x coordinate is (\\d+)$")
    public void player_x_is(int playerX) {
        compareXCoordinate(TileType.PLAYER, playerX);
    }

    @Then("^the player's y coordinate is (\\d+)$")
    public void player_y_is(int playerY) {
        compareYCoordinate(TileType.PLAYER, playerY);
    }

    @Then("^the enemy's x coordinate is (\\d+)$")
    public void enemy_x_is(int enemyX) {
        compareXCoordinate(TileType.ENEMY, enemyX);
    }

    @Then("^the enemy's y coordinate is (\\d+)$")
    public void enemy_y_is(int enemyY) {
        compareYCoordinate(TileType.ENEMY, enemyY);
    }

    @Then("^the goal's x coordinate is (\\d+)$")
    public void goal_x_is(int goalX) {
        compareXCoordinate(TileType.GOAL, goalX);
    }

    @Then("^the goal's y coordinate is (\\d+)$")
    public void goal_y_is(int goalY) {
        compareYCoordinate(TileType.GOAL, goalY);
    }

    @Then("^\\((\\d+), (\\d+)\\) is \"([^\"]*)\"$")
    public void is(int x, int y, String tileChar) {
        char ch = tileChar.charAt(0);
        TileType actualTileType = gameEngine.getTileFromCoordinates(x - COORDINATE_OFFSET, y - COORDINATE_OFFSET);
        assertThat(actualTileType, equalTo(TileType.getTileTypeByChar(ch)));
    }

    @Then("^the error message is displayed$")
    public void the_invalid_character_error_message_is_displayed() {
        assertFalse(exceptionMessage.isEmpty());
    }

    @Then("^the message is: \"(.*)\"$")
    public void the_message_is(String errorMessage) {
        assertThat(exceptionMessage, equalTo(errorMessage));
    }


    @Then("^the malfunctioning reader error message is displayed$")
    public void the_malfunctioning_reader_error_message_is_displayed() {
        assertThat(true, equalTo(gameEngine.isExit()));
    }

    private void compareXCoordinate(TileType tileType, int xCoordinate) {
        assertThat(gameEngine.getXCoordinate(tileType), equalTo(xCoordinate - COORDINATE_OFFSET));
    }

    private void compareYCoordinate(TileType tileType, int yCoordinate) {
        assertThat(gameEngine.getYCoordinate(tileType), equalTo(yCoordinate - COORDINATE_OFFSET));
    }

    private void assertPointsAreEqual(Point originalPoint, TileType tileType) {
        assertThat(new Point(gameEngine.getXCoordinate(tileType), gameEngine.getYCoordinate(tileType)), equalTo(originalPoint));
    }

    private void assertPointsAreNotEqual(Point originalPoint, TileType tileType) {
        assertThat(new Point(gameEngine.getXCoordinate(tileType), gameEngine.getYCoordinate(tileType)), not(equalTo(originalPoint)));
    }
}