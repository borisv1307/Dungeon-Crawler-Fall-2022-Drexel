package engine;

import boardPiece.*;
import enums.Direction;
import enums.Result;
import enums.TileType;

import java.awt.*;
import java.util.List;

public class GameBoard {
    private static final String TILE_TYPE_ERROR = "Get Coordinate not applicable for TileType %s";
    private BoardPiece[][] boardPieces;
    private Player player;
    private Enemy enemy;
    private Goal goal;

    public int getLevelHorizontalDimension() {
        return boardPieces.length;
    }

    public int getLevelVerticalDimension() {
        return boardPieces[0].length;
    }

    public TileType getTileFromCoordinates(final int x, final int y) {
        return boardPieces[x][y].getTileType();
    }

    public Result movement(final TileType tileType, final Direction direction) {
        final Point startingLocation = getBoardPiecePoint(tileType);
        final Point attemptedLocation = new Point(startingLocation.x + direction.getDeltaX(), startingLocation.y + direction.getDeltaY());
        final MovableBoardPiece movableBoardPiece = (MovableBoardPiece) getBoardPiece(tileType);

        return attemptMovement(movableBoardPiece, attemptedLocation);
    }

    public void setBoardPieces(final BoardPiece[][] boardPieces) {
        this.boardPieces = boardPieces;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Point getBoardPiecePoint(final TileType tileType) {
        return getBoardPiece(tileType).getLocation();
    }

    private BoardPiece getBoardPiece(final TileType tileType) {
        if (player != null && player.getTileType() == tileType) {
            return player;
        } else if (enemy != null && enemy.getTileType() == tileType) {
            return enemy;
        } else if (goal != null && goal.getTileType() == tileType) {
            return goal;
        }

        throw new IllegalArgumentException(String.format(TILE_TYPE_ERROR, tileType));
    }

    private Result attemptMovement(MovableBoardPiece movableBoardPiece, Point attemptedLocation) {
        final List<Point> validMoves = movableBoardPiece.getValidMoves(boardPieces);
        Result result = Result.CONTINUE;
        if (validMoves.contains(attemptedLocation)) {
            TileType displacedTileType = movableBoardPiece.setLocation(attemptedLocation, boardPieces).getTileType();
            result = handleLevelCompletion(movableBoardPiece.getTileType(), displacedTileType);
        }
        return result;
    }

    private Result handleLevelCompletion(TileType tileType, TileType displacedTileType) {
        Result result = Result.CONTINUE;

        if (tileType == TileType.PLAYER && displacedTileType == TileType.GOAL) {
            result = Result.WIN;
        } else if (tileType == TileType.ENEMY && displacedTileType == TileType.PLAYER) {
            result = Result.LOSE;
        }

        return result;
    }
}
