package engine;

import entities.Enemy;
import entities.Player;
import wrappers.RandomizerWrapper;

public class CombatEngine {
    RandomizerWrapper randomizerWrapper;
    private Player player;
    private Enemy enemy;
    private String gameStatusString;

    CombatEngine(RandomizerWrapper randomizerWrapper) {
        this.randomizerWrapper = randomizerWrapper;
    }

    public CombatObject doCombat(CombatObject combatObject) {
        this.player = combatObject.player;
        this.enemy = combatObject.enemy;

        int enemyHP = enemy.receiveDamage(player.getAttackValue());
        int playerHP = player.receiveDamage(enemy.getAttackValue());
        gameStatusString = getCombatStatus(player, enemy);

        if (enemyHP <= 0) {
            this.enemy = killEnemy(enemy);
        }

        if (playerHP <= 0) {
            this.player = killPlayer(player);
        }

        return new CombatObject(this.player, this.enemy, gameStatusString);

    }

    Enemy killEnemy(Enemy enemy) {
        gameStatusString = String.format(GameStatus.ENEMY_DEFEATED, enemy.getName());
        return createNewEnemy(0, 0);
    }

    private Enemy createNewEnemy(int x, int y) {
        return randomizerWrapper.getRandomEnemy(x, y);
    }

    private String getCombatStatus(Player player, Enemy enemy) {
        return String.format(GameStatus.DAMAGE_TO_ENEMY, enemy.getName(), player.getAttackValue(), enemy.getArmorClass());
    }

    Player killPlayer(Player player) {
        setGameStatusString(GameStatus.PLAYER_DEFEATED);
        return new Player(player.getOriginX(), player.getOriginY());
    }

    String getGameStatusString() {
        return gameStatusString;
    }

    void setGameStatusString(String string) {
        this.gameStatusString = string;
    }
}
