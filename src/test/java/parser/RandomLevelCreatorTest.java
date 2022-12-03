package parser;

import board.piece.BoardPiece;
import board.piece.BoardPieceFactory;
import engine.GameEngine;
import enums.Result;
import enums.TileType;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class RandomLevelCreatorTest {
    private final int SEED_0 = 0;
    private final int SEED_1 = 1;
    private final int X_DIMENSION = 5;
    private final int Y_DIMENSION = 5;

    private final TileType[][] expectedTilesSeed1 = {
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.EMPTY, TileType.EMPTY, TileType.EMPTY, TileType.WALL},
            {TileType.WALL, TileType.PLAYER, TileType.EMPTY, TileType.ENEMY, TileType.WALL},
            {TileType.WALL, TileType.EMPTY, TileType.EMPTY, TileType.GOAL, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL}};

    private final TileType[][] expectedTilesSeed2 = {{TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.PLAYER, TileType.EMPTY, TileType.ENEMY, TileType.WALL},
            {TileType.WALL, TileType.EMPTY, TileType.GOAL, TileType.EMPTY, TileType.WALL},
            {TileType.WALL, TileType.EMPTY, TileType.EMPTY, TileType.EMPTY, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL}};

    @Test
    public void generate_levels_with_seed_1() {
        GameEngine gameEngine = setUp(SEED_1);
        verifyAddedPieces(gameEngine, expectedTilesSeed2);
    }

    @Test
    public void generate_levels_with_seed_0() {
        GameEngine gameEngine = setUp(SEED_0);
        verifyAddedPieces(gameEngine, expectedTilesSeed1);
    }

    @Test
    public void generate_levels_with_seed_0_twice() {
        GameEngine gameEngine = setUp(SEED_0);
        verifyAddedPieces(gameEngine, expectedTilesSeed1);
        gameEngine.handleLevelCompletion(Result.LOSE);
        verifyAddedPieces(gameEngine, expectedTilesSeed1);
    }

    @Test
    public void generate_levels_increments_seed_by_level() {
        GameEngine gameEngine = setUp(SEED_0);
        verifyAddedPieces(gameEngine, expectedTilesSeed1);
        gameEngine.handleLevelCompletion(Result.WIN);
        verifyAddedPieces(gameEngine, expectedTilesSeed2);
    }

    private void verifyAddedPieces(GameEngine gameEngine, TileType[][] expectedTileTypes) {
        for (int x = 0; x < expectedTileTypes.length; x++) {
            for (int y = 0; y < expectedTileTypes[0].length; y++) {
                BoardPiece actualBoardPiece = gameEngine.getGameBoard().getBoardPieceFromCoordinates(x, y);
                Point expectedLocation = new Point(x, y);
                TileType expectedTileType = expectedTileTypes[x][y];
                assertEquals(expectedTileType, actualBoardPiece.getTileType());
                assertEquals(expectedLocation, actualBoardPiece.getLocation());
            }
        }
    }

    private GameEngine setUp(int seed) {
        RandomLevelCreator randomLevelCreator = new RandomLevelCreator(new BoardPieceFactory(), seed, X_DIMENSION, Y_DIMENSION);
        return new GameEngine(randomLevelCreator);
    }
}
