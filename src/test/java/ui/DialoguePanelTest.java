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

    private Component[] buttons;

    @Before
    public void setUp() throws Exception {
        gameEngine = mock(GameEngine.class);
        dialoguePanel = new DialoguePanel(gameEngine);
        buttons = dialoguePanel.getButtons();
    }

    @Test
    public void dialogue_panel_has_three_buttons() {
        int actual = buttons.length;
        assertEquals(3, actual);
    }

    @Test
    public void button_one_is_created_with_label_choice_one() {
        Button actual = (Button) buttons[0];
        assertEquals("Choice One", actual.getLabel());
    }

    @Test
    public void button_one_has_correct_set_bounds() {
        Button button = (Button) buttons[0];
        Rectangle actual = button.getBounds();
        assertEquals(new Rectangle(100, 100, 80, 30), actual);
    }

    @Test
    public void button_two_is_created_with_label_choice_one() {
        Button actual = (Button) buttons[1];
        assertEquals("Choice Two", actual.getLabel());
    }

    @Test
    public void button_two_has_correct_set_bounds() {
        Button button = (Button) buttons[1];
        Rectangle actual = button.getBounds();
        assertEquals(new Rectangle(150, 100, 80, 30), actual);
    }

    @Test
    public void button_three_is_created_with_label_choice_one() {
        Button actual = (Button) buttons[2];
        assertEquals("Choice Three", actual.getLabel());
    }

    @Test
    public void button_three_has_correct_set_bounds() {
        Button button = (Button) buttons[2];
        Rectangle actual = button.getBounds();
        assertEquals(new Rectangle(200, 100, 80, 30), actual);
    }
}
