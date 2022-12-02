package engine;

import entities.Enemy;
import entities.Player;
import tiles.TileType;
import wrappers.RandomizerWrapper;

import java.util.ArrayList;
import java.util.Arrays;

public class EntityHandler {
    GameEngine gameEngine;
    RandomizerWrapper randomizerWrapper;

    EntityHandler(GameEngine gameEngine, RandomizerWrapper randomizerWrapper) {
        this.gameEngine = gameEngine;
        this.randomizerWrapper = randomizerWrapper;
    }

    public void doCombat(Player player, Enemy enemy) {
        int enemyHP = enemy.receiveDamage(player.getAttackValue());
        int playerHP = player.receiveDamage(enemy.getAttackValue());
        gameEngine.setGameStatus(getCombatStatus(player, enemy));

        if (enemyHP <= 0) {
            killEnemy(enemy);
        }

        if (playerHP <= 0) {
            killPlayer(player);
        }
    }

    ArrayList<Integer> getNewCoordinates() {
        int newXCoordinate = randomizerWrapper.getRandomNewCoordinate(gameEngine.getLevelHorizontalDimension());
        int newYCoordinate = randomizerWrapper.getRandomNewCoordinate(gameEngine.getLevelVerticalDimension());

        while (!newPointIsOpen(newXCoordinate, newYCoordinate)) {
            newXCoordinate = randomizerWrapper.getRandomNewCoordinate(gameEngine.getLevelHorizontalDimension());
            newYCoordinate = randomizerWrapper.getRandomNewCoordinate(gameEngine.getLevelVerticalDimension());
        }

        return new ArrayList<>(Arrays.asList(newXCoordinate, newYCoordinate));
    }

    private boolean newPointIsOpen(int x, int y) {
        TileType tileType = gameEngine.getTileFromCoordinates(x, y);
        return (tileType == TileType.PASSABLE);
    }

    void killEnemy(Enemy enemy) {
        ArrayList<Integer> newCoordinates = getNewCoordinates();
        int x = gameEngine.getEnemyXCoordinate();
        int y = gameEngine.getEnemyYCoordinate();
        gameEngine.removeTile(x, y);
        gameEngine.setGameStatus(String.format(GameStatus.ENEMY_DEFEATED, enemy.getName()));
        createNewEnemy(newCoordinates.get(0), newCoordinates.get(1));
    }

    private void createNewEnemy(int x, int y) {
        gameEngine.setEnemy(randomizerWrapper.getRandomEnemy(x, y));
    }

    private String getCombatStatus(Player player, Enemy enemy) {
        return String.format(GameStatus.DAMAGE_TO_ENEMY, enemy.getName(), player.getAttackValue(), enemy.getArmorClass());
    }

    void killPlayer(Player player) {
        int x = gameEngine.getPlayerXCoordinate();
        int y = gameEngine.getPlayerYCoordinate();
        gameEngine.removeTile(x, y);
        gameEngine.setPlayer(new Player(player.getOriginX(), player.getOriginY()));
        gameEngine.setGameStatus(GameStatus.PLAYER_DEFEATED);
    }


}
