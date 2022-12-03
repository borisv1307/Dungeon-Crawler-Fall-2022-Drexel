package projectile;

import engine.GameEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tiles.TileType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.timeout;

public class ProjectileTest {

    GameEngine gameEngine;
    int startX, startY;

    @Before
    public void init() {
        gameEngine = Mockito.mock(GameEngine.class);
        startX = gameEngine.getPlayerXCoordinate();
        startY = gameEngine.getPlayerYCoordinate();
    }

    @Test
    public void projectile_movement_test() {
        int playerX = gameEngine.getPlayerXCoordinate();
        int playerY = gameEngine.getPlayerYCoordinate();
        gameEngine.keyRight();
        ProjectileShooterFactory.getInstance().getProjectileShooter(playerX + 2, playerY, 10, 20, gameEngine, TileType.LEFT_SHOOTER);
        Mockito.verify(gameEngine, timeout(100).atLeastOnce()).notifyProjectileMovement(any());
    }

    @Test
    public void add_projectile() {
        ProjectileShooterFactory.getInstance().getProjectileShooter(1, 1, 10, 20, gameEngine, TileType.LEFT_SHOOTER);
        Mockito.verify(gameEngine, timeout(100).atLeastOnce()).addProjectile(any());
    }

    @Test
    public void player_reset_on_projectile_move() {
        gameEngine.keyDown();
        addProjectileToEngine(startX - 1, startY);
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_up() {
        addProjectileToEngine(startX, startY - 1);
        gameEngine.keyUp();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_down() {
        addProjectileToEngine(startX, startY + 1);
        gameEngine.keyDown();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_right() {
        addProjectileToEngine(startX + 1, startY);
        gameEngine.keyRight();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_left() {
        addProjectileToEngine(startX - 1, startY);
        gameEngine.keyLeft();
        Assert.assertTrue(playerBackAtStart());
    }

    private void addProjectileToEngine(int x, int y) {
        Projectile projectile = new Projectile(x, y, 1, 1, 100, gameEngine);
        gameEngine.addProjectile(projectile);
    }

    private boolean playerBackAtStart() {
        return gameEngine.getPlayerXCoordinate() == startX && gameEngine.getPlayerYCoordinate() == startY;
    }
}
