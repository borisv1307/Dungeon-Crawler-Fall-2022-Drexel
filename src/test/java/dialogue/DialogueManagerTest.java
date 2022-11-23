package dialogue;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import ui.DialogueManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DialogueManagerTest {
    DialogueManager dialogueManager;
    GameEngine gameEngine;
    Component[] buttons;
    JButton actual;
    Frame dialogueFrame;

    @Before
    public void setUp() throws Exception {
        dialogueManager = new DialogueManager();
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        dialogueFrame = dialogueManager.createFrame("Dialogue Frame");
        buttons = dialogueFrame.getComponents();
    }

    @Test
    public void dialogue_frame_is_not_resizable() {
        assertThat(dialogueFrame.isResizable(), equalTo(false));
    }

    @Test
    public void close_dialogue_frame_when_user_clicks_exit() {
        dialogueFrame.dispatchEvent(new WindowEvent(dialogueFrame, WindowEvent.WINDOW_CLOSING));
        assertFalse(dialogueFrame.isShowing());
    }

    @Test
    public void dialogue_frame_layout_is_flow_layout() {
        assertEquals(dialogueFrame.getLayout().toString(), new FlowLayout().toString());
    }

    @Test
    public void dialogue_frame_has_choice_one_button() {
        actual = (JButton) buttons[0];
        assertEquals("Choice One", actual.getText());
    }

    @Test
    public void dialogue_frame_has_choice_two_button() {
        actual = (JButton) buttons[1];
        assertEquals("Choice Two", actual.getText());
    }

    @Test
    public void dialogue_frame_has_choice_three_button() {
        actual = (JButton) buttons[2];
        assertEquals("Choice Three", actual.getText());
    }
    
}
