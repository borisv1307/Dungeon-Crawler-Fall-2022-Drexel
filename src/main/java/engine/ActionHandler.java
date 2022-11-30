package engine;

import tiles.TileType;

public class ActionHandler {

    private GameEngine gameEngine;

    public ActionHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


    public boolean tileIsPassable(int deltaX, int deltaY) {
        TileType tileType = TileType.PASSABLE;
        return checkTileIsEqual(tileType, convertPositionToTitleType(deltaX, deltaY));

    }

    public boolean tileIsNextLevel(int deltaX, int deltaY) {
        TileType tileType = TileType.NEXT_LEVEL;
        return checkTileIsEqual(tileType, convertPositionToTitleType(deltaX, deltaY));
    }

    private TileType convertPositionToTitleType(int deltaX, int deltaY) {
        return gameEngine.getTileFromCoordinates(gameEngine.getPlayerXCoordinate() + deltaX, gameEngine.getPlayerYCoordinate() + deltaY);
    }

    private boolean checkTileIsEqual(TileType tileTypeOne, TileType tileTypeTwo) {
        return tileTypeOne.equals(tileTypeTwo);
    }

    public void interactWithObject() {
        if (playerIsNextToEnemy()) {
            System.out.println("true");
        }
    }

    private boolean playerIsNextToEnemy() {

        int playerXCoordinate = gameEngine.getPlayerXCoordinate();
        int playerYCoordinate = gameEngine.getPlayerYCoordinate();


        return (playerIsBelowOrAboveTheEnemy(playerXCoordinate, playerYCoordinate) || playerIsLeftOrRightTheEnemy(playerXCoordinate, playerYCoordinate));

    }

    private boolean playerIsBelowOrAboveTheEnemy(int playerXCoordinate, int playerYCoordinate) {
        boolean playerIsAboveEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate, playerYCoordinate + 1).equals(TileType.ENEMY);
        boolean playerIsBelowEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate, playerYCoordinate - 1).equals(TileType.ENEMY);
        return (playerIsAboveEnemy || playerIsBelowEnemy);

    }

    private boolean playerIsLeftOrRightTheEnemy(int playerXCoordinate, int playerYCoordinate) {
        boolean playerIsLeftEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate - 1, playerYCoordinate).equals(TileType.ENEMY);
        boolean playerIsRightEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate + 1, playerYCoordinate).equals(TileType.ENEMY);

        return (playerIsLeftEnemy || playerIsRightEnemy);

    }

}
