package parser;

import boardPiece.*;
import engine.GameBoard;
import engine.GameEngine;


public abstract class LevelCreator {
    protected final BoardPieceFactory boardPieceFactory;

    protected LevelCreator(BoardPieceFactory boardPieceFactory) {
        this.boardPieceFactory = boardPieceFactory;
    }

    public abstract void createLevel(final GameEngine gameEngine, final int level);

    protected void setBoardPiece(GameBoard gameBoard, BoardPiece boardPiece) {
        if (boardPiece instanceof Player) {
            gameBoard.setPlayer((Player) boardPiece);
        } else if (boardPiece instanceof Enemy) {
            gameBoard.setEnemy((Enemy) boardPiece);
        } else if (boardPiece instanceof Goal) {
            gameBoard.setGoal((Goal) boardPiece);
        }
    }
}
