package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DialogueManagerTest {
    DialogueManager dialogueManager;
    JButton responseButtonOne;
    JButton responseButtonTwo;
    JButton responseButtonThree;

    JTextField textFieldActual;
    Frame dialogueFrame;

    @Before
    public void setUp() throws Exception {
        dialogueManager = new DialogueManager();
        dialogueFrame = dialogueManager.createFrame();

        responseButtonOne = (JButton) dialogueFrame.getComponent(0);
        responseButtonTwo = (JButton) dialogueFrame.getComponent(1);
        responseButtonThree = (JButton) dialogueFrame.getComponent(2);
        textFieldActual = (JTextField) dialogueFrame.getComponent(3);
    }

    @Test
    public void create_dialogue_frame_only_once() {
        final DialogueManager mockDialogueManager = Mockito.mock(DialogueManager.class);
        dialogueFrame = mockDialogueManager.createFrame();
        Mockito.verify(mockDialogueManager, Mockito.times(1)).createFrame();
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
    public void dialogue_frame_layout_is_grid_bag() {
        assertEquals(dialogueFrame.getLayout().toString(), new GridBagLayout().toString());
    }

    @Test
    public void dialogue_frame_has_response_button_with_dialogue_one_response_one() {
        assertEquals("Can you help me find the mythical code?", responseButtonOne.getText());
    }

    @Test
    public void dialogue_frame_has_response_button_with_dialogue_one_response_two() {
        assertEquals("What are you doing here?", responseButtonTwo.getText());
    }

    @Test
    public void dialogue_frame_has_response_button_with_dialogue_one_response_three() {
        assertEquals("Actually, I do not need help. Goodbye!", responseButtonThree.getText());
    }

    @Test
    public void dialogue_frame_text_area_read_only() {
        assertFalse(textFieldActual.isEditable());
    }

    @Test
    public void display_first_dialogue_display_to_text_field() {
        dialogueManager.updateJTextField(1);
        assertEquals("Halt! Who goes there? Are ye a player or a bug?", textFieldActual.getText());
    }

    @Test
    public void display_second_dialogue_display_to_text_field() {
        dialogueManager.updateJTextField(2);
        assertEquals("I can help you find the code! But only after my watch ends.", textFieldActual.getText());
    }

    @Test
    public void display_third_dialogue_display_to_text_field() {
        dialogueManager.updateJTextField(3);
        assertEquals("I am here to keep watch over this level, watch your step now.", textFieldActual.getText());
    }

    @Test
    public void click_dialogue_one_response_one_current_dialogue_should_be_dialogue_two() {
        responseButtonOne.doClick();
        Dialogue actual = dialogueManager.getCurrentDialogue();
        assertEquals(2, actual.getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_two_current_dialogue_should_be_dialogue_three() {
        responseButtonTwo.doClick();
        Dialogue actual = dialogueManager.getCurrentDialogue();
        assertEquals(3, actual.getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_one_then_dialogue_two_response_one_current_dialogue_should_be_dialogue_one() {
        responseButtonOne.doClick();
        responseButtonOne.doClick();
        Dialogue actual = dialogueManager.getCurrentDialogue();
        assertEquals(1, actual.getDialogueID());
    }

    @Test
    public void clicks_dialogue_one_response_three_then_dialogue_frame_closes() {
        responseButtonThree.doClick();
        assertFalse(dialogueFrame.isShowing());
    }

    @Test
    public void click_five_responses_then_last_response_closes_dialogue() {
        responseButtonOne.doClick();
        responseButtonTwo.doClick();
        responseButtonThree.doClick();
        responseButtonOne.doClick();
        responseButtonTwo.doClick();
        assertFalse(dialogueFrame.isShowing());
    }


}
