package entity;

import wrappers.EnemyRandomWrapper;

public class Enemy {

    private int level;
    private int healthPoint;
    private int attackPoint;

    public Enemy(int level) {
        this.level = level;
    }

    public void createEnemy(EnemyRandomWrapper enemyRandomWrapper) {
        enemyRandomWrapper.generateIntValue(this);

    }


    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getLevel() {
        return level;
    }
}
