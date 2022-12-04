package entity;

public abstract class CharacterObject {
    int level;
    int maxHealthPoint;
    int currentHealthPoint;
    int attackPoint;


    CharacterObject() {
        setToDefaultStatus();
    }

    void setToDefaultStatus() {
        level = 1;
        maxHealthPoint = 10;
        attackPoint = 10;
        currentHealthPoint = maxHealthPoint;
    }


    public int getLevel() {
        return level;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getCurrentHealthPoint() {
        return currentHealthPoint;
    }

    public void setCurrentHealthPoint(int maxHealthPoint) {
        currentHealthPoint = maxHealthPoint;
    }


    public void takeDamage(int healthPointLost) {
        currentHealthPoint -= healthPointLost;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

}
