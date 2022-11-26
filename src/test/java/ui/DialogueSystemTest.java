package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DialogueSystemTest {
    DialogueSystem dialogueSystem;
    JButton responseButtonOne;
    JButton responseButtonTwo;
    JButton responseButtonThree;

    JTextArea textFieldActual;
    DialogueFrame dialogueFrame;
    String eventName;

    @Before
    public void setUp() throws Exception {
        dialogueSystem = new DialogueSystem();
        dialogueFrame = dialogueSystem.launchDialogueFrame();

        responseButtonOne = (JButton) dialogueFrame.getComponent(0);
        responseButtonTwo = (JButton) dialogueFrame.getComponent(1);
        responseButtonThree = (JButton) dialogueFrame.getComponent(2);
        textFieldActual = (JTextArea) dialogueFrame.getComponent(3);
        eventName = "Click Event";
    }

    @Test
    public void create_dialogue_frame_only_once() {
        dialogueSystem = Mockito.mock(DialogueSystem.class);
        dialogueFrame = dialogueSystem.launchDialogueFrame();
        Mockito.verify(dialogueSystem, Mockito.times(1)).launchDialogueFrame();
    }

    @Test
    public void first_button_event_is_click_event() {
        assertEquals(eventName, responseButtonOne.getActionCommand());
    }

    @Test
    public void second_button_event_is_click_event() {
        assertEquals(eventName, responseButtonTwo.getActionCommand());
    }

    @Test
    public void third_button_event_is_click_event() {
        assertEquals(eventName, responseButtonThree.getActionCommand());
    }

    @Test
    public void when_created_dialogue_frame_has_response_button_with_dialogue_one_response_one() {
        assertEquals("Can you help me find the mythical code?", responseButtonOne.getText());
    }

    @Test
    public void when_created_dialogue_frame_has_response_button_with_dialogue_one_response_two() {
        assertEquals("What are you doing here?", responseButtonTwo.getText());
    }

    @Test
    public void dialogue_frame_has_response_button_with_dialogue_one_response_three() {
        assertEquals("Actually, I do not need help. Goodbye!", responseButtonThree.getText());
    }

    @Test
    public void display_first_dialogue_to_text_field() {
        assertEquals("Halt! Who goes there? Are ye a player or a bug?", textFieldActual.getText());
    }

    @Test
    public void update_dialogue_system_and_display_second_dialogue_to_text_field() {
        dialogueSystem.updateJTextFieldContent(2, dialogueFrame);
        assertEquals("I can help you find the code! But only after my watch ends.", textFieldActual.getText());
    }

    @Test
    public void update_dialogue_system_and_display_third_dialogue_to_text_field() {
        dialogueSystem.updateJTextFieldContent(3, dialogueFrame);
        assertEquals("I am here to keep watch over this level, watch your step now.", textFieldActual.getText());
    }

    @Test
    public void click_first_dialogue_response_one_next_dialogue_should_be_dialogue_ID_two() {
        responseButtonOne.doClick();
        Dialogue actual = dialogueSystem.getCurrentDialogue();
        assertEquals(2, actual.getDialogueID());
    }

    @Test
    public void dialogue_two_should_button_three_should_not_be_visible() {
        responseButtonOne.doClick();
        assertFalse(responseButtonThree.isShowing());
    }

    @Test
    public void click_dialogue_one_response_two_current_dialogue_should_be_dialogue_ID_three() {
        responseButtonTwo.doClick();
        Dialogue actual = dialogueSystem.getCurrentDialogue();
        assertEquals(3, actual.getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_one_then_dialogue_two_response_one_current_dialogue_should_be_dialogue_ID_one() {
        responseButtonOne.doClick();
        responseButtonOne.doClick();
        Dialogue actual = dialogueSystem.getCurrentDialogue();
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
        responseButtonOne.doClick();
        assertFalse(dialogueFrame.isShowing());
    }


}


