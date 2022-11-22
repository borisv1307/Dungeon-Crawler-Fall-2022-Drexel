package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DialoguePanelTest {
    DialoguePanel dialoguePanel;
    GameEngine gameEngine;
    int width = 50;
    int height = 60;

    @Before
    public void setUp() throws Exception {
        gameEngine = mock(GameEngine.class);
        dialoguePanel = new DialoguePanel(gameEngine);
        dialoguePanel.setSize(width, height);
    }

    @Test
    public void dialoguePanel_has_three_buttons() {
        int actual = dialoguePanel.getComponentCount();
        assertEquals(3, actual);
    }

    @Test
    public void button_one_is_created_with_label_choice_one() {
        assertEquals("java.awt.Button[button0,0,0,0x0,invalid,label=Choice One]", dialoguePanel.getComponent(0).toString());
    }


}
