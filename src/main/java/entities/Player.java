package entities;

public class Player extends Entity {
    public Player(int x, int y) {
        super(x, y);
        this.hitPoints = 10;
        this.armorClass = 2;
        this.attackValue = 4;
    }

    public Player copyPlayerToNewLocation(int x, int y) {
        Player newPlayer = new Player(x, y);
        newPlayer.hitPoints = this.hitPoints;
        newPlayer.armorClass = this.armorClass;
        newPlayer.attackValue = this.attackValue;
        return newPlayer;
    }
}
