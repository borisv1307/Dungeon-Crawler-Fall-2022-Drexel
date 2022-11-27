package entity;

public class Player {

    private int level;
    private int healthPoint;
    private int attackPoint;

    public Player() {
        level = 1;
        healthPoint = 10;
        attackPoint = 10;
    }

    public void levelUp() {
        level += 1;
        levelStatChange();
    }

    private void levelStatChange() {
        int baseState = 10;
        setHealthPoint(baseState + level * 5);
        setAttackPoint(baseState + level * 2);
    }

    public int getLevel() {
        return level;
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

}
