package parser;

import board.piece.BoardPiece;
import board.piece.BoardPieceFactory;
import engine.GameEngine;
import enums.TileType;
import org.junit.Test;

import java.awt.*;

import static enums.Result.LOSE;
import static enums.Result.WIN;
import static org.junit.Assert.*;

public class RandomLevelCreatorTest {
    private final int SEED_ZERO = 0;
    private final int SEED_ONE = 1;
    private final int X_DIMENSION_TWO = 2;
    private final int Y_DIMENSION_TWO = 2;
    private final int X_DIMENSION_FIVE = 5;
    private final int Y_DIMENSION_FIVE = 5;
    private final int X_DIMENSION_TWENTY = 20;
    private final int Y_DIMENSION_TEN = 10;

    @Test
    public void generate_level_with_enough_space_for_tiles() {
        GameEngine gameEngine = setUp(SEED_ONE, 3, 5);
        assertFalse(gameEngine.isExit());
    }

    @Test
    public void generate_level_no_space_for_tiles() {
        GameEngine gameEngine = setUp(SEED_ONE, X_DIMENSION_TWO, Y_DIMENSION_TWO);
        assertTrue(gameEngine.isExit());
    }

    @Test
    public void generate_levels_with_seed_1() {
        GameEngine gameEngine = setUp(SEED_ONE, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        int expectedObstacleCount = 0;
        verifyTiles(gameEngine, X_DIMENSION_FIVE, Y_DIMENSION_FIVE, expectedObstacleCount);
    }

    private void verifyTiles(GameEngine gameEngine, int xDimension, int yDimension, int expectedObstacleCount) {
        TileType[][] tiles = getTiles(gameEngine);
        verifyWalls(tiles, expectedObstacleCount);
        verifyOne(tiles, TileType.GOAL);
        verifyOne(tiles, TileType.ENEMY);
        verifyOne(tiles, TileType.PLAYER);
    }

    private void verifyOne(TileType[][] tiles, TileType tileType) {
        int actualCount = 0;
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y] == tileType) {
                    actualCount++;
                }
            }
        }
        assertEquals(1, actualCount);
    }

    private void verifyWalls(TileType[][] tiles, int expectedObstacleCount) {
        int actualCount = 0;
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (isOnPerimeter(x, y, tiles)) {
                    assertEquals(TileType.WALL, tiles[x][y]);
                } else if (tiles[x][y] == TileType.WALL) {
                    actualCount++;
                }
            }
        }
        assertEquals(expectedObstacleCount, actualCount);
    }

    private boolean isOnPerimeter(int x, int y, TileType[][] tiles) {
        return y == 0 || x == 0 || x == tiles.length - 1 || y == tiles[0].length - 1;
    }

    @Test
    public void generate_levels_with_seed_0() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        verifyTiles(gameEngine, X_DIMENSION_FIVE, Y_DIMENSION_FIVE, 0);
    }

    @Test
    public void generate_levels_with_seed_0_twice() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        TileType[][] tiles = getTiles(gameEngine);
        gameEngine.handleLevelCompletion(LOSE);
        verifyAddedPieces(gameEngine, tiles);
    }

    @Test
    public void generate_levels_increments_seed_by_level() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        TileType[][] tilesLevelOne = getTiles(gameEngine);
        verifyTiles(gameEngine, X_DIMENSION_FIVE, Y_DIMENSION_FIVE, 0);
        gameEngine.handleLevelCompletion(WIN);
        TileType[][] tilesLevelTwo = getTiles(gameEngine);
        verifyTiles(gameEngine, X_DIMENSION_FIVE, Y_DIMENSION_FIVE, 1);
        verifyLevelsAreDifferent(tilesLevelOne, tilesLevelTwo);
    }

    private void verifyLevelsAreDifferent(TileType[][] tilesLevelOne, TileType[][] tilesLevelTwo) {
        boolean same = true;
        for (int x = 0; x < tilesLevelOne.length; x++) {
            for (int y = 0; y < tilesLevelOne[0].length; y++) {
                if (tilesLevelOne[x][y] != tilesLevelTwo[x][y]) {
                    same = false;
                    break;
                }
            }
        }
        assertFalse(same);
    }

    @Test
    public void level_1_has_no_walls_as_obstacles() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        verifyWallsAsObstaclesCount(gameEngine, 0);
    }

    @Test
    public void level_2_has_no_walls_as_obstacles() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(2, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 1);
    }

    @Test
    public void level_3_has_no_walls_as_obstacles() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(3, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 2);
    }

    @Test
    public void level_4_has_no_walls_as_obstacles() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(4, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 3);

    }

    @Test
    public void level_5_has_no_walls_as_obstacles() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_FIVE, Y_DIMENSION_FIVE);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(5, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 4);
    }

    @Test
    public void level_1_has_no_walls_as_obstacles_20_by_10() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_TWENTY, Y_DIMENSION_TEN);
        verifyWallsAsObstaclesCount(gameEngine, 0);
    }

    @Test
    public void level_2_has_no_walls_as_obstacles_20_by_10() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_TWENTY, Y_DIMENSION_TEN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(2, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 8);
    }

    @Test
    public void level_3_has_no_walls_as_obstacles_20_by_10() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_TWENTY, Y_DIMENSION_TEN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(3, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 16);
    }

    @Test
    public void level_4_has_no_walls_as_obstacles_20_by_10() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_TWENTY, Y_DIMENSION_TEN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(4, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 24);

    }

    @Test
    public void level_5_has_no_walls_as_obstacles_20_by_10() {
        GameEngine gameEngine = setUp(SEED_ZERO, X_DIMENSION_TWENTY, Y_DIMENSION_TEN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        gameEngine.handleLevelCompletion(WIN);
        assertEquals(5, gameEngine.getLevel());
        verifyWallsAsObstaclesCount(gameEngine, 32);
    }

    private void verifyWallsAsObstaclesCount(GameEngine gameEngine, int expectedWallCount) {
        int actualWallCount = 0;
        for (int x = 1; x < gameEngine.getLevelHorizontalDimension() - 1; x++) {
            for (int y = 1; y < gameEngine.getLevelVerticalDimension() - 1; y++) {
                if (gameEngine.getTileFromCoordinates(x, y) == TileType.WALL) {
                    actualWallCount++;
                }
            }
        }
        assertEquals(expectedWallCount, actualWallCount);
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

    private GameEngine setUp(int seed, int xDimension, int yDimension) {
        RandomLevelCreator randomLevelCreator = new RandomLevelCreator(new BoardPieceFactory(), seed, xDimension, yDimension);
        return new GameEngine(randomLevelCreator);
    }

    private TileType[][] getTiles(GameEngine gameEngine) {
        TileType[][] tiles = new TileType[gameEngine.getLevelHorizontalDimension()][gameEngine.getLevelVerticalDimension()];
        for (int x = 0; x < gameEngine.getLevelHorizontalDimension(); x++) {
            for (int y = 0; y < gameEngine.getLevelVerticalDimension(); y++) {
                tiles[x][y] = gameEngine.getTileFromCoordinates(x, y);
            }
        }
        return tiles;
    }
}
