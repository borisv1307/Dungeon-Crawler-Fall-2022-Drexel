package ui;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ButtonActionListenerTest {
    final DialogueSystem dialogueSystem = DialogueSystem.getInstance();
    JTextArea dialogueTextArea;

    @Before
    public void setUp() {
        dialogueTextArea = dialogueSystem.dialogueFrame.getDialogueTextArea();
    }

    @Test
    public void button_listener_updates_dialogue_frame_when_user_clicks_a_response_button() {
        final DialogueButton buttonOne = dialogueSystem.getButton(0);
        dialogueSystem.initiateDialogueFrame();

        String originalButtonContent = buttonOne.getButtonContent();
        String originalDialogueTextAreaContent = dialogueTextArea.getText();

        buttonOne.doClick();

        assertNotEquals(originalButtonContent, buttonOne.getButtonContent());
        assertNotEquals(originalDialogueTextAreaContent, dialogueTextArea.getText());
    }

    @Test
    public void button_listener_closes_dialogue_frame_when_user_clicks_a_response_with_ID_negative_one() {
        dialogueSystem.initiateDialogueFrame();
        final DialogueButton buttonThree = dialogueSystem.getButton(2);
        buttonThree.doClick();
        assertFalse(dialogueSystem.dialogueFrame.isShowing());
    }

    @Test
    public void button_listener_sets_is_dialogue_active_to_false_when_user_clicks_a_response_with_ID_negative_one() {
        dialogueSystem.initiateDialogueFrame();
        final DialogueButton buttonThree = dialogueSystem.getButton(2);
        buttonThree.doClick();
        assertFalse(dialogueSystem.isDialogueActive());
    }

}
