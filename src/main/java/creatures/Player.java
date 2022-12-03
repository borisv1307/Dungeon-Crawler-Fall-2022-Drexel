package creatures;

public class Player extends Creature {
    int spawnCoordinateX;
    int spawnCoordinateY;

    public Player(int x, int y) {
        super(x, y);
        this.hitPoints = 10;
        this.armorClass = 2;
        this.attackValue = 4;
        this.spawnCoordinateX = x;
        this.spawnCoordinateY = y;
    }

    public int getSpawnCoordinateX() {
        return spawnCoordinateX;
    }

    public int getSpawnCoordinateY() {
        return spawnCoordinateY;
    }

    @Override
    public boolean equals(Object object) {
        try {
            Player playerObject = (Player) object;
            return super.equals(playerObject) && compareObjectProperties(playerObject);
        } catch (ClassCastException e) {
            return super.equals(object);
        }
    }

    private boolean compareObjectProperties(Player playerObject) {
        if (this.spawnCoordinateX != playerObject.spawnCoordinateX) {
            return false;
        }
        return this.spawnCoordinateY == playerObject.spawnCoordinateY;
    }
}
