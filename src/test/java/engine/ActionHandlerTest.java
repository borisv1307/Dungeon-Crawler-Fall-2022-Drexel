package engine;

import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ActionHandlerTest {

    GameEngine gameEngine;
    ActionHandler actionHandler;

    @Before
    public void setUp() {
        gameEngine = ObjectFactory.getDefaultGameEngine();
        actionHandler = new ActionHandler(gameEngine);
    }

    @Test
    public void tile_is_passable() {
        boolean actual = actionHandler.tileIsPassable(1, 0);
        assertEquals(true, actual);
    }

    @Test
    public void tile_is_not_passable() {
        boolean actual = actionHandler.tileIsPassable(0, 1);
        assertEquals(false, actual);
    }

    @Test
    public void tile_is_next_level() {
        boolean actual = actionHandler.tileIsNextLevel(-1, -8);
        assertEquals(true, actual);
    }

    @Test
    public void tile_is_not_next_level() {
        boolean actual = actionHandler.tileIsNextLevel(-1, -1);
        assertEquals(false, actual);
    }

}
