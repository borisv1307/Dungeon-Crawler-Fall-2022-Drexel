package parser;

import engine.GameEngine;
import org.mockito.Mockito;
import tiles.TileType;
import values.TestingTunableParameters;
import values.TunableParameters;
import wrappers.RandomWrapper;
import wrappers.ReaderWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LevelCreatorITHelper {

    protected static final int ONE = 1;
    protected GameEngine gameEngine;
    protected String exceptionMessage;
    protected IOException ioException;
    private ReaderWrapper readerWrapper;
    private RandomWrapper randomWrapper;

    protected List<String> createSimpleLevel() {
        List<String> levelStrings = new ArrayList<>();
        levelStrings.add("XXXX");
        levelStrings.add("X PX");
        levelStrings.add("XXXX");
        return levelStrings;
    }

    protected void writeLevelFile(List<String> levelStrings)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(getFilePath(), "UTF-8");
        for (String levelString : levelStrings) {
            writer.println(levelString);
        }
        writer.close();
    }

    private String getFilePath() {
        return TestingTunableParameters.FILE_LOCATION_PREFIX + ONE + TunableParameters.FILE_NAME_SUFFIX;
    }

    protected void createLevel() throws Throwable {
        LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX,
                new ReaderWrapper());
        try {
            gameEngine = new GameEngine(levelCreator, new RandomWrapper());
        } catch (IllegalArgumentException e) {
            exceptionMessage = e.getMessage();
        }
    }

    protected void playerIsLocatedAt(int playerX, int playerY) throws Throwable {
        checkPlayerXCoordinate(playerX);
        checkPlayerYCoordinate(playerY);
    }

    protected void checkPlayerXCoordinate(int playerX) throws Throwable {
        assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX));
    }

    protected void checkPlayerYCoordinate(int playerY) throws Throwable {
        assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY));
    }

    protected void checkTileTypeByLocation(int x, int y, char tileChar) throws Throwable {
        TileType actualTileType = gameEngine.getTileFromCoordinates(x, y);
        assertThat(actualTileType, equalTo(TileType.getTileTypeByChar(tileChar)));
    }

    protected void checkAllTiles(List<String> levelStrings) throws Throwable {
        for (int row = 0; row < levelStrings.size(); row++) {
            String rowString = levelStrings.get(row);
            for (int col = 0; col < rowString.length(); col++) {
                checkTileTypeByLocation(col, row, rowString.charAt(col));
            }
        }
    }

    protected void createLevelWithMalfunctioningFileReader() throws Throwable {
        ioException = Mockito.mock(IOException.class);
        readerWrapper = Mockito.mock(ReaderWrapper.class);
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(readerWrapper.createBufferedReader(Mockito.anyString())).thenReturn(bufferedReader);
        Mockito.doThrow(ioException).when(bufferedReader).readLine();
        LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, readerWrapper);
        gameEngine = new GameEngine(levelCreator, new RandomWrapper());
    }

}
