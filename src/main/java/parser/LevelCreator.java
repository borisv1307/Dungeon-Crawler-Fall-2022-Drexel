package parser;

import board.piece.BoardPieceFactory;
import engine.GameEngine;


public abstract class LevelCreator {
    protected final BoardPieceFactory boardPieceFactory;

    protected LevelCreator(BoardPieceFactory boardPieceFactory) {
        this.boardPieceFactory = boardPieceFactory;
    }

    public abstract void createLevel(final GameEngine gameEngine, final int level);
}
