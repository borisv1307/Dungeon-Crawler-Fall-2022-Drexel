package engine;

import entities.Enemy;
import entities.Player;
import wrappers.RandomizerWrapper;

public class CombatEngine {
    private RandomizerWrapper randomizerWrapper;
    private String gameStatusString;

    CombatEngine(RandomizerWrapper randomizerWrapper) {
        this.randomizerWrapper = randomizerWrapper;
    }

    public CombatObject doCombat(CombatObject combatObject) {
        Player player = combatObject.player;
        Enemy enemy = combatObject.enemy;

        int enemyHP = enemy.receiveDamage(player.getAttackValue());
        int playerHP = player.receiveDamage(enemy.getAttackValue());
        setGameStatusString(getCombatStatus(player, enemy));

        if (enemyHP <= 0) {
            enemy = killEnemy(enemy);
        }

        if (playerHP <= 0) {
            player = killPlayer(player);
        }

        return new CombatObject(player, enemy, gameStatusString);
    }

    Enemy killEnemy(Enemy enemy) {
        setGameStatusString(String.format(GameStatus.ENEMY_DEFEATED, enemy.getName()));
        return createNewEnemy(0, 0);
    }

    Player killPlayer(Player player) {
        setGameStatusString(GameStatus.PLAYER_DEFEATED);
        return new Player(player.getSpawnCoordinateX(), player.getSpawnCoordinateY());
    }

    String getGameStatusString() {
        return gameStatusString;
    }

    private void setGameStatusString(String string) {
        this.gameStatusString = string;
    }

    private Enemy createNewEnemy(int x, int y) {
        return randomizerWrapper.getRandomEnemy(x, y);
    }

    private String getCombatStatus(Player player, Enemy enemy) {
        return String.format(GameStatus.DAMAGE_TO_ENEMY, enemy.getName(), player.getAttackValue(), enemy.getArmorClass());
    }
}
