package engine;

import entity.Enemy;
import entity.Player;
import parser.LevelCreator;
import values.TileColorMap;

public class CombatManagement {
    private GameEngine gameEngine;

    private LevelCreator levelCreator;

    private Player player;


    public CombatManagement(GameEngine gameEngine, LevelCreator levelCreator, Player player) {
        this.gameEngine = gameEngine;
        this.levelCreator = levelCreator;
        this.player = player;
    }

    public void attack(Enemy enemy) {
        int playerCurrentHealth = player.getCurrentHealthPoint();
        int enemyCurrentHealth = enemy.getCurrentHealthPoint();

        while (playerCurrentHealth != 0 && enemyCurrentHealth != 0) {
            enemyCurrentHealth -= player.getAttackPoint();
            managePlayerHealth(enemy);
        }

        battlerResult();
    }

    void managePlayerHealth(Enemy enemy) {
        player.takeDamage(enemy.getAttackPoint());
        TileColorMap.changePlayerHpBar(player);
    }

    private void battlerResult() {
        if (player.getCurrentHealthPoint() <= 0) {
            lose();
        } else {
            win();
        }
    }

    private void win() {
        player.levelUp();
        levelCreator.createLevel(gameEngine, 1);
    }

    private void lose() {
        levelCreator.createLevel(gameEngine, 4);
        player.resetPlayerStatus();
    }
}
