package projectile;

public class Projectile {
    private int x, y, deltaX, deltaY;

    public Projectile(int startX, int startY, int deltaX, int deltaY) {
        this.x = startX;
        this.y = startY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public boolean playerCollision(int playerX, int playerY) {
        return playerX == x && playerY == y;
    }
}
