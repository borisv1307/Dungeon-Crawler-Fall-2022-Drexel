package parser;

import board.GameBoard;
import board.piece.BoardPieceFactory;
import engine.GameEngine;
import enums.TileType;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLevelCreator extends LevelCreator {
    private static final Logger LOGGER = Logger.getLogger(FileLevelCreator.class.getName());
    private static final String FILE_NAME_SUFFIX = TunableParameters.FILE_NAME_SUFFIX;
    private final String fileLocationPrefix;
    private final ReaderWrapper readerWrapper;

    public FileLevelCreator(String fileLocationPrefix, ReaderWrapper readerWrapper, BoardPieceFactory boardPieceFactory) {
        super(boardPieceFactory);
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

        readGameBoard(reader, gameEngine);
    }

    private void readGameBoard(BufferedReader reader, GameEngine gameEngine) {
        try {
            final int xDimension = getNextInteger(reader);
            final int yDimension = getNextInteger(reader);
            final GameBoard gameBoard = new GameBoard(boardPieceFactory, xDimension, yDimension);
            fillBoardPieces(reader, gameBoard);
            gameEngine.setGameBoard(gameBoard);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            gameEngine.setExit(true);
        } finally {
            closeBufferedReader(reader, gameEngine);
        }
    }

    private int getNextInteger(BufferedReader reader) throws IOException {
        int integer = 0;
        String line = reader.readLine();
        if (line != null) {
            integer = Integer.parseInt(line);
        }
        return integer;
    }

    private void fillBoardPieces(BufferedReader reader, GameBoard gameBoard) throws IOException {
        String line;
        int y = 0;
        while ((line = reader.readLine()) != null) {
            int x = 0;
            for (char ch : line.toCharArray()) {
                final TileType tileType = TileType.getTileTypeByChar(ch);
                final Point location = new Point(x, y);
                gameBoard.addBoardPiece(tileType, location);
                x++;
            }
            y++;
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
        return fileLocationPrefix + level + FILE_NAME_SUFFIX;
    }
}
