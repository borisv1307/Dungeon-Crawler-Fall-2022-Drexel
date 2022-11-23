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

import static org.junit.Assert.assertEquals;

public class LaserHandlerTest {

    private final int TILE_WIDTH = 10;
    private final int TILE_HEIGHT = 20;

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
        assertEquals(laser.getX(), 10);
        assertEquals(laser.getY(), 10);
    }


    @Test
    public void laser_is_created_on_space_press(){
        int playerXCoordinate = gameEngine.getPlayerXCoordinate();
        int playerYCoordinate = gameEngine.getPlayerYCoordinate();
        gameEngine.keySpace();
        LaserHandler.Laser laser = gameEngine.laserHandler.lasers.get(0);
        assertEquals(laser.getX(), playerXCoordinate);
        assertEquals(laser.getY(), playerYCoordinate);
    }
    @Test
    public void laser_is_painted_at_correct_spot(){
        Graphics graphics = Mockito.mock(Graphics.class);
        gameEngine.keySpace();
        TilePainter tilePainter = new TilePainter();
        tilePainter.paintLasers(graphics, gameEngine.getLasers(), TILE_WIDTH, TILE_HEIGHT);
        int correctXPosition = gameEngine.getPlayerXCoordinate() * TILE_WIDTH + (TILE_WIDTH / 2) - gameEngine.getLasers().get(0).getWidth() / 2;
        int correctYPosition = gameEngine.getPlayerYCoordinate() * TILE_HEIGHT - gameEngine.getLasers().get(0).getHeight();
        Mockito.verify(graphics).fillRect(correctXPosition, correctYPosition, 10, 10);
    }

    @Test
    public void laser_moves_position_to_top(){
        gameEngine.keySpace();
        LaserHandler.Laser laser = gameEngine.getLasers().get(0);
        int initialX = laser.getX();
        int initialY = laser.getY();
        gameEngine.laserHandler.progressLasers();
        assertEquals(initialY - 1, laser.getY());
        assertEquals(initialX, laser.getX());

    }

}
