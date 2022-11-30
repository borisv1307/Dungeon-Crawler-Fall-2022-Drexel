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
        return true;
    }

}
