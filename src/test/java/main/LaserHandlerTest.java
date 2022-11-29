package main;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import ui.TilePainter;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import java.awt.*;

import static org.junit.Assert.*;

public class LaserHandlerTest {

    private final int TILE_WIDTH = 10;

    private final int LASER_WIDTH = 2;
    private final int LASER_HEIGHT = 4;

    GameEngine gameEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine(
                new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
    }

    @Test
    public void laser_handler_creates_laser() {
        LaserHandler laserHandler = new LaserHandler();
        LaserHandler.Laser laser = laserHandler.laserFactory(10, 10);
        assertEquals(10, laser.getX());
        assertEquals(10, laser.getY());
    }


    @Test
    public void laser_is_created_on_space_press(){
        LaserHandler.Laser laser = createAndGetFirstLaser();
        assertNotNull(laser);
    }

    @Test
    public void laser_position_is_in_own_grid() {
        int playerXCoordinate = gameEngine.getPlayerXCoordinate();
        int playerYCoordinate = gameEngine.getPlayerYCoordinate();
        LaserHandler.Laser laser = createAndGetFirstLaser();
        assertEquals(laser.getX(), playerXCoordinate * TunableParameters.TILE_TO_LASER_WIDTH);
        assertEquals(laser.getY(), playerYCoordinate * TunableParameters.TILE_TO_LASER_HEIGHT);
    }
    @Test
    public void laser_is_painted_at_correct_spot() {
        Graphics graphics = Mockito.mock(Graphics.class);
        LaserHandler.Laser laser = createAndGetFirstLaser();
        TilePainter tilePainter = new TilePainter();
        tilePainter.paintLasers(graphics, gameEngine.laserHandler.getLasers(), LASER_WIDTH, LASER_HEIGHT, TILE_WIDTH);
        int correctXPosition = laser.getX() * LASER_WIDTH + (TILE_WIDTH / 2) - (LASER_WIDTH / 2);
        int correctYPosition = laser.getY() * LASER_HEIGHT;
        Mockito.verify(graphics).fillRect(correctXPosition, correctYPosition, LASER_WIDTH, LASER_HEIGHT);

    }
    @Test
    public void laser_moves_position_to_top(){
        LaserHandler.Laser laser = createAndGetFirstLaser();
        int initialX = laser.getX();
        int initialY = laser.getY();
        gameEngine.laserHandler.progressLasers();
        assertEquals(initialY - 1, laser.getY());
        assertEquals(initialX, laser.getX());
    }

    @Test
    public void laser_is_removed_on_contact_with_wall(){
        LaserHandler.Laser laser = createAndGetFirstLaser();
        while(laser.getY() >= 5){
            gameEngine.laserHandler.progressLasers();
        }
        assert(gameEngine.laserHandler.getLasers().isEmpty());
    }

    public LaserHandler.Laser createAndGetFirstLaser(){
        gameEngine.keySpace();
        return gameEngine.laserHandler.getLasers().get(0);

    }
}
