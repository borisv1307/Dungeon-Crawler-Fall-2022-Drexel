package engine;

import entities.Enemy;
import entities.Player;

public class CombatEngine {
    GameEngine gameEngine;

    CombatEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void doCombat(Player player, Enemy enemy) {
        int enemyHP = enemy.receiveDamage(player.getAttackValue());
        int playerHP = player.receiveDamage(enemy.getAttackValue());
        gameEngine.setGameStatus(getCombatStatus(player, enemy));

        if (enemyHP <= 0) {
            gameEngine.enemyKilled();
        }

        if (playerHP <= 0) {
            gameEngine.playerKilled();
        }
    }

    private String getCombatStatus(Player player, Enemy enemy) {
        return String.format(GameStatus.DAMAGE_TO_ENEMY, enemy.getName(), player.getAttackValue(), enemy.getArmorClass());
    }
}
