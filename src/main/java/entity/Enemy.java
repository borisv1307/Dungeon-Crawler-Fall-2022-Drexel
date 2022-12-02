package entity;

import engine.CombatManagement;
import wrappers.EnemyRandomWrapper;

public class Enemy extends CharacterObject {

    public Enemy(int level) {
        this.level = level;
    }

    public void createEnemy(EnemyRandomWrapper enemyRandomWrapper) {
        enemyRandomWrapper.generateIntValue(this);

    }
    public void attackedBy(CombatManagement combatManagement){
        combatManagement.attack(this);
    }

}
