package projectile;

import engine.GameEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.timeout;

public class ProjectileTest {

    GameEngine mockGameEngine;
    GameEngine engine;
    Projectile projectile;
    Projectile mockProjectile;
    int startX, startY;

    @Before
    public void init() {
        mockGameEngine = Mockito.mock(GameEngine.class);
        engine = new GameEngine(new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
        startX = engine.getPlayerXCoordinate();
        startY = engine.getPlayerYCoordinate();
        projectile = new Projectile(1, 1, 1, 1, 1, engine);
        mockProjectile = Mockito.mock(Projectile.class);
    }

    @Test
    public void engine_saves_projectile() {
        engine.addProjectile(projectile);
        Assert.assertTrue(engine.getProjectiles().contains(projectile));
    }

    @Test
    public void engine_removes_stopped_projectile() {
        projectile.stop();
        Assert.assertFalse(engine.getProjectiles().contains(projectile));
    }

    @Test
    public void shooter_add_projectile() {
        ProjectileShooterFactory.getInstance().getProjectileShooter(1, 1, 1, 1, mockGameEngine, TileType.LEFT_SHOOTER);
        Mockito.verify(mockGameEngine, timeout(50).atLeast(10)).addProjectile(any());
    }

    @Test
    public void projectile_location_check() {
        engine.notifyProjectileMovement(mockProjectile);
        Mockito.verify(mockProjectile, atLeastOnce()).getX();
        Mockito.verify(mockProjectile, atLeastOnce()).getY();
    }

    @Test
    public void projectile_stops_at_wall() {
        engine.addTile(1, 1, TileType.NOT_PASSABLE);
        engine.addProjectile(mockProjectile);
        engine.notifyProjectileMovement(mockProjectile);
        Mockito.verify(mockProjectile).stop();
    }

    @Test
    public void projectile_movement() {
        Projectile projectileForMock = new Projectile(1, 1, 1, 1, 1, mockGameEngine);
        Mockito.verify(mockGameEngine, timeout(100).atLeastOnce()).notifyProjectileMovement(projectileForMock);
    }

    @Test
    public void projectile_stops_max_iterations() {
        Projectile projectileForMock = new Projectile(1, 1, 1, 1, 1, mockGameEngine);
        Mockito.verify(mockGameEngine, timeout(100)).removeProjectile(projectileForMock);
    }

    @Test
    public void player_reset_on_projectile_move() {
        engine.keyLeft();
        addProjectileToEngine(startX - 1, startY);
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_up() {
        addProjectileToEngine(startX, startY - 1);
        engine.keyUp();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_down() {
        addProjectileToEngine(startX, startY + 1);
        engine.keyDown();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_right() {
        addProjectileToEngine(startX + 1, startY);
        engine.keyRight();
        Assert.assertTrue(playerBackAtStart());
    }

    @Test
    public void player_reset_on_collision_left() {
        addProjectileToEngine(startX - 1, startY);
        engine.keyLeft();
        Assert.assertTrue(playerBackAtStart());
    }

    private void addProjectileToEngine(int x, int y) {
        Projectile projectile = new Projectile(x, y, 1, 1, 100, mockGameEngine);
        engine.addProjectile(projectile);
        engine.notifyProjectileMovement(projectile);
    }

    private boolean playerBackAtStart() {
        return engine.getPlayerXCoordinate() == startX && engine.getPlayerYCoordinate() == startY;
    }
}
