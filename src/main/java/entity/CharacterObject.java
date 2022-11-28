package entity;

public abstract class CharacterObject {
    int level;
    int healthPoint;
    int attackPoint;

    public CharacterObject() {
        level = 1;
        healthPoint = 10;
        attackPoint = 10;
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
