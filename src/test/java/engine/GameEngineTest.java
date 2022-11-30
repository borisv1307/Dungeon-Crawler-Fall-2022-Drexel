package engine;

import board.GameBoard;
import board.piece.BoardPiece;
import board.piece.Enemy;
import board.piece.Goal;
import board.piece.Player;
import enums.TileType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import ui.GameFrame;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;

    GameEngine gameEngine;

    @Before
    public void setUp() {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        gameEngine.setGameBoard(new GameBoard());
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
        gameEngine.getGameBoard().setBoardPieces(new BoardPiece[ONE][TWO]);
        int actualVertical = gameEngine.getLevelVerticalDimension();
        int actualHorizontal = gameEngine.getLevelHorizontalDimension();
        assertThat(actualVertical, equalTo(TWO));
        assertThat(actualHorizontal, equalTo(ONE));
    }

    @Test
    public void add_and_get_player_coordinates() {
        TileType tileType = TileType.PLAYER;
        gameEngine.getGameBoard().setPlayer(new Player(new Point(ZERO, ONE)));
        int actualX = gameEngine.getXCoordinate(tileType);
        int actualY = gameEngine.getYCoordinate(tileType);
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ONE));
    }

    @Test
    public void add_and_get_enemy_coordinates() {
        TileType tileType = TileType.ENEMY;
        gameEngine.getGameBoard().setEnemy(new Enemy(new Point(ZERO, ONE)));
        int actualX = gameEngine.getXCoordinate(tileType);
        int actualY = gameEngine.getYCoordinate(tileType);
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ONE));
    }

    @Test
    public void add_and_get_goal_coordinates() {
        TileType tileType = TileType.GOAL;
        gameEngine.getGameBoard().setGoal(new Goal(new Point(ZERO, ONE)));
        int actualX = gameEngine.getXCoordinate(tileType);
        int actualY = gameEngine.getYCoordinate(tileType);
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ONE));
    }

    @Test
    public void set_and_get_exit() {
        boolean exit = true;
        gameEngine.setExit(exit);
        boolean actual = gameEngine.isExit();
        assertThat(actual, equalTo(exit));
    }
}
