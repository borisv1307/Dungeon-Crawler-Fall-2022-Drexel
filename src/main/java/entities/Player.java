package entities;

public class Player extends Entity {
    int originX;
    int originY;

    public Player(int x, int y) {
        super(x, y);
        this.hitPoints = 10;
        this.armorClass = 2;
        this.attackValue = 4;
        this.originX = x;
        this.originY = y;
    }

    public Player copyPlayerToNewLocation(int x, int y) {
        Player newPlayer = new Player(x, y);
        newPlayer.hitPoints = this.hitPoints;
        newPlayer.armorClass = this.armorClass;
        newPlayer.attackValue = this.attackValue;
        newPlayer.originX = this.originX;
        newPlayer.originY = this.originY;
        return newPlayer;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    @Override
    public boolean equals(Object object) {
        try {
            Player playerObject = (Player) object;
            return compareObjectProperties(playerObject);
        } catch (ClassCastException e) {
            return super.equals(object);
        }
    }

    private boolean compareObjectProperties(Player playerObject) {
        if (this.originX != playerObject.originX) {
            return false;
        }
        return this.originY == playerObject.originY;
    }
}