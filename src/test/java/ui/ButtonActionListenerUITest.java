package ui;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ButtonActionListenerUITest {
    DialogueSystem dialogueSystem;
    JTextArea dialogueTextArea;
    DialogueButton dialogueButtonOne;
    DialogueButton dialogueButtonThree;

    @Before
    public void setUp() {
        dialogueSystem = new DialogueSystem();
        dialogueButtonOne = dialogueSystem.getButton(0);
        dialogueButtonThree = dialogueSystem.getButton(2);
        dialogueTextArea = dialogueSystem.getActiveDialoguePanelTextArea();

        dialogueSystem.initiateDialogueFrame();
    }

    @Test
    public void button_listener_updates_dialogue_frame_when_user_clicks_a_response_button() {
        String originalButtonContent = dialogueButtonOne.getButtonContent();
        String originalDialogueTextAreaContent = dialogueTextArea.getText();

        dialogueButtonOne.doClick();

        assertNotEquals(originalButtonContent, dialogueButtonOne.getButtonContent());
        assertNotEquals(originalDialogueTextAreaContent, dialogueTextArea.getText());
    }

    @Test
    public void button_listener_closes_dialogue_frame_and_sets_dialogue_system_inactive_when_user_clicks_a_response_with_ID_negative_one() {
        dialogueButtonThree.doClick();
        assertFalse(dialogueSystem.isDialogueActive());
        assertFalse(dialogueSystem.dialogueFrame.isShowing());
    }
}
