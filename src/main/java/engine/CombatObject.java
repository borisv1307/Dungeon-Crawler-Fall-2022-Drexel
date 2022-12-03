package engine;

import creatures.Enemy;
import creatures.Player;

public class CombatObject {
    Player player;
    Enemy enemy;
    String gameStatusString;

    CombatObject(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    CombatObject(Player player, Enemy enemy, String gameStatusString) {
        this.player = player;
        this.enemy = enemy;
        this.gameStatusString = gameStatusString;
    }
}
