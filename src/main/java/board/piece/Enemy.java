package board.piece;

import enums.TileType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends MovableBoardPiece {
    private static final TileType TILE_TYPE = TileType.ENEMY;

    public Enemy(Point location) {
        super(location, TILE_TYPE);
    }

    @Override
    protected boolean isValidMove(BoardPiece[][] gameBoard, Point attemptedLocation) {
        TileType attemptedLocationType = getTileType(gameBoard, attemptedLocation);
        return attemptedLocationType != TileType.WALL && attemptedLocationType != TileType.GOAL;
    }

    public Point getNextMove(BoardPiece[][] boardPieces, Point playerLocation, Point goalLocation) {
        List<Point> surroundingMovesToEnemy = getSurroundingMoves(boardPieces, location);
        if (surroundingMovesToEnemy.contains(playerLocation)) {
            return playerLocation;
        }

        final List<Point> nextPossibleMoves = getMovesTowardsPlayer(boardPieces, playerLocation);
        final List<Point> directionClosestToGoal = getMovesTowardsPlayerClosestToGoal(boardPieces, goalLocation, nextPossibleMoves);

        if (directionClosestToGoal.isEmpty()) {
            return location;
        } else {
            return getMoveInPriorityOrder(surroundingMovesToEnemy, directionClosestToGoal);
        }
    }

    private Point getMoveInPriorityOrder(List<Point> surroundingMovesToEnemy, List<Point> directionClosestToGoal) {
        for (Point surroundingMove : surroundingMovesToEnemy) {
            if (directionClosestToGoal.contains(surroundingMove)) {
                return surroundingMove;
            }
        }
        throw new IllegalStateException("Closest locations not in surrounding locations.");
    }

    private List<Point> getMovesTowardsPlayerClosestToGoal(BoardPiece[][] boardPieces, Point goalLocation, List<Point> nextPossibleMoves) {
        int[][] stepsToLocation = initStepsToLocation(boardPieces, goalLocation);
        return findShortestPathToLocation(stepsToLocation, boardPieces, goalLocation, nextPossibleMoves);
    }

    private List<Point> getMovesTowardsPlayer(BoardPiece[][] boardPieces, Point playerLocation) {
        int[][] stepsToLocation = initStepsToLocation(boardPieces, playerLocation);
        List<Point> nextMoves = getSurroundingMovesNotReached(boardPieces, location, stepsToLocation);
        return findShortestPathToLocation(stepsToLocation, boardPieces, playerLocation, nextMoves);
    }

    private List<Point> findShortestPathToLocation(int[][] stepsToLocation, BoardPiece[][] boardPieces, Point moveTowards, List<Point> moveFrom) {
        List<Point> currentDepthLocations = getListOfGoalLocation(moveTowards);
        List<Point> possibleMoves = new ArrayList<>();

        while (!currentDepthLocations.isEmpty() && possibleMoves.isEmpty()) {
            List<Point> nextDepthLocations = new ArrayList<>();
            for (Point currentLocation : currentDepthLocations) {
                int currentStepsFromPlayer = stepsToLocation[currentLocation.x][currentLocation.y] + 1;
                List<Point> nextMoves = getSurroundingMovesNotReached(boardPieces, currentLocation, stepsToLocation);
                for (Point nextMove : nextMoves) {
                    stepsToLocation[nextMove.x][nextMove.y] = currentStepsFromPlayer;
                    if (moveFrom.contains(nextMove)) {
                        possibleMoves.add(nextMove);
                    }
                }
                nextDepthLocations.addAll(nextMoves);
            }
            currentDepthLocations = nextDepthLocations;
        }
        return possibleMoves;
    }

    private List<Point> getListOfGoalLocation(Point moveTowards) {
        List<Point> locations = new ArrayList<>();
        locations.add(moveTowards);
        return locations;
    }

    private int[][] initStepsToLocation(BoardPiece[][] boardPieces, Point moveTowards) {
        int[][] stepsToLocation = new int[boardPieces.length][boardPieces[0].length];
        stepsToLocation[moveTowards.x][moveTowards.y] = 1;
        return stepsToLocation;
    }

    private List<Point> getSurroundingMovesNotReached(BoardPiece[][] boardPieces, Point currentLocation, int[][] stepsToLocation) {
        List<Point> nextMoves = getSurroundingMoves(boardPieces, currentLocation);
        nextMoves.removeIf(attemptedLocation -> stepsToLocation[attemptedLocation.x][attemptedLocation.y] != 0);
        return nextMoves;
    }
}
