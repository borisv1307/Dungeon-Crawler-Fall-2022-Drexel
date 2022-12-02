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

    public ActionHandler(GameEngine gameEngine, LevelCreator  levelCreator, Player player) {
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
        if (playerIsNextToEnemy()) {
            Enemy enemy = new Enemy(gameEngine.getLevel());
            enemy.createEnemy(new EnemyRandomWrapper());
            enemy.createEnemy(new EnemyRandomWrapper());
            CombatManagement combatManagement = new CombatManagement(gameEngine,levelCreator, player, enemy, this);
            enemy.attackedBy(combatManagement);
        }
    }

    public boolean playerIsNextToEnemy() {

        int playerXCoordinate = gameEngine.getPlayerXCoordinate();
        int playerYCoordinate = gameEngine.getPlayerYCoordinate();


        return (playerIsBelowOrAboveTheEnemy(playerXCoordinate, playerYCoordinate) || playerIsLeftOrRightTheEnemy(playerXCoordinate, playerYCoordinate));

    }

    private TileType convertPositionToTitleType(int deltaX, int deltaY) {
        return gameEngine.getTileFromCoordinates(gameEngine.getPlayerXCoordinate() + deltaX, gameEngine.getPlayerYCoordinate() + deltaY);
    }

    private boolean checkTileIsEqual(TileType tileTypeOne, TileType tileTypeTwo) {
        return tileTypeOne.equals(tileTypeTwo);
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
