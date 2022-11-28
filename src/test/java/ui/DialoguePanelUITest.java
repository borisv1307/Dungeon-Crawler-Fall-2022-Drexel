package ui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DialoguePanelUITest {
    DialogueSystem dialogueSystem;
    DialogueFrame dialogueFrame;

    DialogueButton buttonOne;
    DialogueButton buttonTwo;
    DialogueButton buttonThree;

    @Before
    public void setUp() {
        dialogueSystem = DialogueSystem.getInstance();
        dialogueFrame = new DialogueFrame(new DialoguePanel());

        buttonOne = dialogueSystem.getButton(0);
        buttonTwo = dialogueSystem.getButton(1);
        buttonThree = dialogueSystem.getButton(2);
    }

    @Test
    public void click_dialogue_one_response_one_then_dialogue_should_be_ID_two() {
        dialogueSystem.initiateDialogueFrame();
        buttonOne.doClick();
        assertEquals(2, dialogueSystem.getCurrentDialogue().getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_one_then_dialogue_should_be_ID_three() {
        dialogueSystem.initiateDialogueFrame();
        buttonTwo.doClick();
        assertEquals(3, dialogueSystem.getCurrentDialogue().getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_three_then_dialogue_should_be_ID_reset_to_one_after_window_closes() {
        dialogueSystem.initiateDialogueFrame();
        buttonThree.doClick();
        assertEquals(1, dialogueSystem.getCurrentDialogue().getDialogueID());
    }
}
