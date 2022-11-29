package engine;

import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class ActionHandlerTest {

    GameEngine gameEngine;
    ActionHandler actionHandler;
    @Before
    public void setUp(){
        gameEngine = ObjectFactory.getDefaultGameEngine();
        actionHandler = new ActionHandler(gameEngine);
    }

    @Test
    public void this_should_not_be_a_problem(){
        assertEquals(1, 1);
    }
}
