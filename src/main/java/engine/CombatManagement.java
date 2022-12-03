package engine;

import entity.Enemy;
import entity.Player;
import parser.LevelCreator;

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

            if (enemyCurrentHealth <= 0) {
                break;
            }
            playerCurrentHealth -= enemy.getAttackPoint();
        }

        if (playerCurrentHealth <= 0) {
            levelCreator.createLevel(gameEngine, 4);
        } else {
            win();
        }
    }

    private void win() {
        player.levelUp();
        levelCreator.createLevel(gameEngine, 1);
    }
}
