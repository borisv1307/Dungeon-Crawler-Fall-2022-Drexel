package parser;

import board.piece.*;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

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

    @Then("^the board dimensions are x = (\\d+) and y = (\\d+)$")
    public void the_board_dimensions_are_x_and_y(int xDimension, int yDimension) {
        int actualXDimension = gameEngine.getLevelHorizontalDimension();
        int actualYDimension = gameEngine.getLevelVerticalDimension();

        assertEquals(xDimension, actualXDimension);
        assertEquals(yDimension, actualYDimension);
    }

    @Then("^\\((\\d+), (\\d+)\\) is a Wall$")
    public void is_a_Wall(int xCoordinate, int yCoordinate) {
        BoardPiece boardPiece = getBoardPiece(xCoordinate, yCoordinate);
        assertSame(Wall.class, boardPiece.getClass());
    }

    @Then("^\\((\\d+), (\\d+)\\) is Empty$")
    public void is_Empty(int xCoordinate, int yCoordinate) throws Throwable {
        BoardPiece boardPiece = getBoardPiece(xCoordinate, yCoordinate);
        assertSame(Empty.class, boardPiece.getClass());
    }

    @Then("^\\((\\d+), (\\d+)\\) is a Player$")
    public void is_a_Player(int xCoordinate, int yCoordinate) throws Throwable {
        BoardPiece boardPiece = getBoardPiece(xCoordinate, yCoordinate);
        assertSame(Player.class, boardPiece.getClass());
    }

    @Then("^\\((\\d+), (\\d+)\\) is an Enemy$")
    public void is_an_Enemy(int xCoordinate, int yCoordinate) throws Throwable {
        BoardPiece boardPiece = getBoardPiece(xCoordinate, yCoordinate);
        assertSame(Enemy.class, boardPiece.getClass());
    }

    @Then("^\\((\\d+), (\\d+)\\) is a Goal$")
    public void is_a_Goal(int xCoordinate, int yCoordinate) throws Throwable {
        BoardPiece boardPiece = getBoardPiece(xCoordinate, yCoordinate);
        assertSame(Goal.class, boardPiece.getClass());
    }

    @Then("^starting from the top-left:$")
    public void starting_from_the_top_left() {
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

    private BoardPiece getBoardPiece(int xCoordinate, int yCoordinate) {
        return gameEngine.getGameBoard().getBoardPieceFromCoordinates(xCoordinate - COORDINATE_OFFSET, yCoordinate - COORDINATE_OFFSET);
    }
}