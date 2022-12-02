package projectile;

import engine.GameEngine;
import tiles.TileType;

public class ProjectileShooterFactory {

    private static ProjectileShooterFactory instance;

    public static ProjectileShooterFactory getInstance() {
        if (instance == null) {
            instance = new ProjectileShooterFactory();
        }
        return instance;
    }

    public ProjectileShooter getProjectileShooter(int x, int y, int interval, int projectileInterval, GameEngine engine, TileType tileType) throws IllegalArgumentException {
        switch (tileType) {
            case DOWN_SHOOTER:
                return new DownProjectileShooter(x, y, interval, projectileInterval, engine);

            case UP_SHOOTER:
                return new UpProjectileShooter(x, y, interval, projectileInterval, engine);

            case LEFT_SHOOTER:
                return new LeftProjectileShooter(x, y, interval, projectileInterval, engine);

            case RIGHT_SHOOTER:
                return new RightProjectileShooter(x, y, interval, projectileInterval, engine);
        }
        throw new IllegalArgumentException("Invalid TileType");
    }
}
