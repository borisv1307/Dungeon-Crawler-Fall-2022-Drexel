package entities;

public class Enemy extends Entity{
    Enemy(int x, int y){
        super(x,y);
    }

    public void setStartingStats(int hitPoints, int armorClass, int attackValue) {
        this.hitPoints = hitPoints;
        this.armorClass = armorClass;
        this.attackValue = attackValue;
    }
}
