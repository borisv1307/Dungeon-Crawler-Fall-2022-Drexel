package parser;

import engine.GameEngine;
import tiles.TileType;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLevelCreator implements LevelCreator {
    private static final Logger LOGGER = Logger.getLogger(LevelCreator.class.getName());

    final String fileLocationPrefix;
    final String fileNameSuffix = TunableParameters.FILE_NAME_SUFFIX;
    final ReaderWrapper readerWrapper;

    public FileLevelCreator(String fileLocationPrefix, ReaderWrapper readerWrapper) {
        this.fileLocationPrefix = fileLocationPrefix;
        this.readerWrapper = readerWrapper;
    }

    @Override
    public void createLevel(final GameEngine gameEngine, final int level) {
        BufferedReader reader;
        try {
            reader = readerWrapper.createBufferedReader(getFilePath(level));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            gameEngine.setExit(true);
            return;
        }
        try {
            String line;
            int xDimension = 0;
            int yDimension = 0;
            line = reader.readLine();
            if (line != null) {
                xDimension = Integer.parseInt(line);
            }
            line = reader.readLine();
            if (line != null) {
                yDimension = Integer.parseInt(line);
            }
            final TileType[][] gameBoard = new TileType[xDimension][yDimension];
            int y = 0;
            while ((line = reader.readLine()) != null) {
                int x = 0;
                for (char ch : line.toCharArray()) {
                    final TileType tileType = TileType.getTileTypeByChar(ch);
                    gameBoard[x][y] = TileType.getTileTypeByChar(ch);
                    if (tileType != TileType.PASSABLE && tileType != TileType.NOT_PASSABLE) {
                        gameEngine.setPlayableObject(x, y, tileType);
                    }
                    x++;
                }
                y++;
            }
            gameEngine.setBoard(gameBoard);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            gameEngine.setExit(true);
        } finally {
            closeBufferedReader(reader, gameEngine);
        }
    }

    private void closeBufferedReader(BufferedReader reader, GameEngine gameEngine) {
        try {
            reader.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            gameEngine.setExit(true);
        }
    }

    String getFilePath(int level) {
        return fileLocationPrefix + level + fileNameSuffix;
    }
}
