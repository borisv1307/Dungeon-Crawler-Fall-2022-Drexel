package engine;

import entity.Enemy;
import entity.Player;
import parser.LevelCreator;
import tiles.TileType;
import wrappers.EnemyRandomWrapper;

public class ActionHandler {

    private GameEngine gameEngine;

    private LevelCreator levelCreator;
    private Player player;

    public ActionHandler(GameEngine gameEngine, LevelCreator levelCreator, Player player) {
        this.gameEngine = gameEngine;
        this.levelCreator = levelCreator;
        this.player = player;
    }


    public boolean tileIsPassable(int deltaX, int deltaY) {
        TileType tileType = TileType.PASSABLE;
        return checkTileIsEqual(tileType, convertPositionToTitleType(deltaX, deltaY));

    }

    public boolean tileIsNextLevel(int deltaX, int deltaY) {
        TileType tileType = TileType.NEXT_LEVEL;
        return checkTileIsEqual(tileType, convertPositionToTitleType(deltaX, deltaY));
    }

    public void interactWithObject() {
        if (playerIsNextTo(TileType.ENEMY)) {
            Enemy enemy = new Enemy(player.getLevel());
            enemy.createEnemy(new EnemyRandomWrapper());
            CombatManagement combatManagement = new CombatManagement(gameEngine, levelCreator, player);
            enemy.attackedBy(combatManagement);
        }

    }

    public boolean playerIsNextTo(TileType tileType) {
        int playerXCoordinate = gameEngine.getPlayerXCoordinate();
        int playerYCoordinate = gameEngine.getPlayerYCoordinate();


        return (playerIsBelowOrAboveTheEnemy(playerXCoordinate, playerYCoordinate, tileType) || playerIsLeftOrRightTheEnemy(playerXCoordinate, playerYCoordinate, tileType));
    }


    private TileType convertPositionToTitleType(int deltaX, int deltaY) {
        return gameEngine.getTileFromCoordinates(gameEngine.getPlayerXCoordinate() + deltaX, gameEngine.getPlayerYCoordinate() + deltaY);
    }

    private boolean checkTileIsEqual(TileType tileTypeOne, TileType tileTypeTwo) {
        return tileTypeOne.equals(tileTypeTwo);
    }

    private boolean playerIsBelowOrAboveTheEnemy(int playerXCoordinate, int playerYCoordinate, TileType tileType) {
        boolean playerIsAboveEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate, playerYCoordinate + 1).equals(tileType);
        boolean playerIsBelowEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate, playerYCoordinate - 1).equals(tileType);
        return (playerIsAboveEnemy || playerIsBelowEnemy);

    }

    private boolean playerIsLeftOrRightTheEnemy(int playerXCoordinate, int playerYCoordinate, TileType tileType) {
        boolean playerIsLeftEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate - 1, playerYCoordinate).equals(tileType);
        boolean playerIsRightEnemy = gameEngine.getTileFromCoordinates(playerXCoordinate + 1, playerYCoordinate).equals(tileType);

        return (playerIsLeftEnemy || playerIsRightEnemy);

    }

}
