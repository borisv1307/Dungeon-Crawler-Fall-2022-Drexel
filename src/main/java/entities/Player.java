package entities;

public class Player extends Entity{
    public Player(int x, int y){
        super(x, y);
        this.hitPoints = 10;
        this.armorClass = 5;
        this.attackValue = 4;
    }
}
