package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DialoguePanelTest {
    DialoguePanel dialoguePanel;
    GameEngine gameEngine;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Component[] buttons;

    @Before
    public void setUp() throws Exception {
        gameEngine = mock(GameEngine.class);
        dialoguePanel = new DialoguePanel(gameEngine);
        buttons = dialoguePanel.getButtons();
        buttonOne = (Button) buttons[0];
        buttonTwo = (Button) buttons[1];
        buttonThree = (Button) buttons[2];
    }

    @Test
    public void dialogue_panel_has_three_buttons() {
        int actual = buttons.length;
        assertEquals(3, actual);
    }

    @Test
    public void button_one_is_created_with_label_choice_one() {
        assertEquals("Choice One", buttonOne.getLabel());
    }

    @Test
    public void button_one_has_correct_set_bounds() {
        Rectangle actual = buttonOne.getBounds();
        assertEquals(new Rectangle(100, 100, 80, 30), actual);
    }

    @Test
    public void button_two_is_created_with_label_choice_one() {
        assertEquals("Choice Two", buttonTwo.getLabel());
    }

    @Test
    public void button_two_has_correct_set_bounds() {
        Rectangle actual = buttonTwo.getBounds();
        assertEquals(new Rectangle(150, 100, 80, 30), actual);
    }

    @Test
    public void button_three_is_created_with_label_choice_one() {
        assertEquals("Choice Three", buttonThree.getLabel());
    }

    @Test
    public void button_three_has_correct_set_bounds() {
        Rectangle actual = buttonThree.getBounds();
        assertEquals(new Rectangle(200, 100, 80, 30), actual);
    }
}
