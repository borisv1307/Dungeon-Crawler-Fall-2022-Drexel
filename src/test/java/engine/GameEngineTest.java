package engine;

import board.GameBoard;
import board.piece.BoardPieceFactory;
import enums.Result;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import ui.GameFrame;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class GameEngineTest {
    private static final int ONE = 1;
    private static final int TWO = 2;

    private GameEngine gameEngine;

    @Before
    public void setUp() {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        int level = 1;
        Mockito.verify(levelCreator, Mockito.times(level)).createLevel(gameEngine, level);
    }

    @Test
    public void run() {
        GameFrame gameFrame = Mockito.mock(GameFrame.class);
        Component component = Mockito.mock(Component.class);
        Mockito.when(gameFrame.getComponents()).thenReturn(new Component[]{component});
        gameEngine.run(gameFrame);
        Mockito.verify(component, Mockito.times(1)).repaint();
    }

    @Test
    public void set_and_get_dimension() {
        GameBoard gameBoard = new GameBoard(Mockito.mock(BoardPieceFactory.class), ONE, TWO);
        gameEngine.setGameBoard(gameBoard);
        int actualVertical = gameEngine.getLevelVerticalDimension();
        int actualHorizontal = gameEngine.getLevelHorizontalDimension();
        assertThat(actualVertical, equalTo(TWO));
        assertThat(actualHorizontal, equalTo(ONE));
    }

    @Test
    public void exit_after_five_levels() {
        gameEngine.handleLevelCompletion(Result.LOSE);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.LOSE);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.WIN);
        assertTrue(gameEngine.isExit());
        assertEquals(6, gameEngine.getLevel());
    }

    @Test
    public void not_exit_after_four_levels() {
        gameEngine.handleLevelCompletion(Result.LOSE);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.LOSE);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.WIN);
        gameEngine.handleLevelCompletion(Result.WIN);
        assertFalse(gameEngine.isExit());
        assertEquals(5, gameEngine.getLevel());
    }

    @Test
    public void set_and_get_exit() {
        boolean exit = true;
        gameEngine.setExit(exit);
        boolean actual = gameEngine.isExit();
        assertThat(actual, equalTo(exit));
    }
}
