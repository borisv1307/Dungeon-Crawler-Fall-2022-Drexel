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
    Component[] components;
    JButton actualJButton;
    JTextField textFieldActual;
    Frame dialogueFrame;

    @Before
    public void setUp() throws Exception {
        dialogueManager = new DialogueManager();
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        dialogueFrame = dialogueManager.createFrame("Dialogue Frame");
        textFieldActual = (JTextField) dialogueFrame.getComponent(3);
        components = dialogueFrame.getComponents();
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
        assertEquals(dialogueFrame.getLayout().toString(), new GridBagLayout().toString());
    }

    @Test
    public void dialogue_frame_has_choice_one_button() {
        actualJButton = (JButton) components[0];
        assertEquals("Choice One", actualJButton.getText());
    }

    @Test
    public void dialogue_frame_has_choice_two_button() {
        actualJButton = (JButton) components[1];
        assertEquals("Choice Two", actualJButton.getText());
    }

    @Test
    public void dialogue_frame_has_choice_three_button() {
        actualJButton = (JButton) components[2];
        assertEquals("Choice Three", actualJButton.getText());
    }

    @Test
    public void dialogue_frame_text_area_read_only() {
        assertFalse(textFieldActual.isEditable());
    }

    @Test
    public void display_first_dialogue_display_to_text_field() {
        dialogueManager.updateJTextField(0);
        assertEquals("Hello, Player what can I help you with?", textFieldActual.getText());
    }

    @Test
    public void display_second_dialogue_display_to_text_field() {
        dialogueManager.updateJTextField(1);
        assertEquals("I can help you find the code, you just have to sniff it out!", textFieldActual.getText());
    }

    @Test
    public void display_third_dialogue_display_to_text_field() {
        dialogueManager.updateJTextField(2);
        assertEquals("I am here to keep watch over this level, watch your step now.", textFieldActual.getText());
    }


}
