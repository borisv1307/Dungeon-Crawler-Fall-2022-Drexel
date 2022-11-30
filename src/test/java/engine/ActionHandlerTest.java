package engine;

import main.ObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
public class ActionHandlerTest {

    ActionHandler actionHandler;

    @Before
    public void setUp(){
        actionHandler = new ActionHandler(ObjectFactory.getDefaultGameEngine());
    }

    @Test
    public void number_is_equal_one(){
        int number =1;
        assertSame(1,number);
    }
}
