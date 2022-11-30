package engine;

import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ActionHandlerTest {

    ActionHandler actionHandler;

    @Before
    public void setUp(){
        actionHandler = new ActionHandler(ObjectFactory.getDefaultGameEngine());
    }
    @Test
    public void interact_with_enemy(){
        actionHandler.playerIsNextToEnemy();
        assertTrue(actionHandler.playerIsNextToEnemy());
    }
}
