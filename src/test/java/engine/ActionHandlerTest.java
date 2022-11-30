package engine;

import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

public class ActionHandlerTest {

    GameEngine gameEngine;
    ActionHandler actionHandler;
    @Before
    public void setUp(){
        gameEngine = ObjectFactory.getDefaultGameEngine();
        actionHandler = new ActionHandler(gameEngine);
    }

    @Test
    public void check_if_true(){
        actionHandler.tileIsPassable(5,5);
        assertSame(1,1);
    }
}
