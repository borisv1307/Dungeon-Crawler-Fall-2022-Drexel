package projectile;

import engine.GameEngine;

public class UpProjectileShooter extends ProjectileShooter {
    public UpProjectileShooter(int x, int y, int interval, int projectileInterval, GameEngine engine) {
        super(x, y, 0, -1, interval, projectileInterval, engine);
    }
}
