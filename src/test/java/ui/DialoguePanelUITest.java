package ui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DialoguePanelUITest {
    DialogueSystem dialogueSystem;
    DialogueButton responseButtonOne;
    DialogueButton responseButtonTwo;
    DialogueButton responseButtonThree;
    Dialogue currentDialogue;

    @Before
    public void setUp() {
        dialogueSystem = new DialogueSystem();

        responseButtonOne = dialogueSystem.getButton(0);
        responseButtonTwo = dialogueSystem.getButton(1);
        responseButtonThree = dialogueSystem.getButton(2);

        dialogueSystem.initiateDialogueFrame();
    }

    @Test
    public void click_dialogue_one_response_one_then_current_dialogue_should_be_ID_two() {
        responseButtonOne.doClick();
        currentDialogue = dialogueSystem.getCurrentDialogue();

        assertEquals(2, currentDialogue.getDialogueID());
        assertTrue(dialogueSystem.isDialogueActive());
    }

    @Test
    public void click_dialogue_one_response_two_then_current_dialogue_should_be_ID_three() {
        responseButtonTwo.doClick();

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertEquals(3, currentDialogue.getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_three_then_current_dialogue_should_reset_to_ID_one_and_dialogue_window_closes() {
        responseButtonThree.doClick();

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertFalse(dialogueSystem.isDialogueActive());
        assertFalse(dialogueSystem.getDialogueFrame().isShowing());
        assertEquals(1, currentDialogue.getDialogueID());
    }


    @Test
    public void click_dialogue_one_response_two_then_dialogue_two_response_three_then_current_dialogue_should_be_ID_three() {
        responseButtonTwo.doClick();
        responseButtonThree.doClick();

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertEquals(5, currentDialogue.getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_one_then_dialogue_two_response_one_then_current_dialogue_should_be_ID_one() {
        responseButtonOne.doClick();
        responseButtonOne.doClick();

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertEquals(1, currentDialogue.getDialogueID());
    }

    @Test
    public void click_dialogue_one_response_one_dialogue_two_response_two_then_current_dialogue_should_be_ID_three() {
        responseButtonOne.doClick();
        responseButtonTwo.doClick();

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertEquals(3, currentDialogue.getDialogueID());
    }

    @Test
    public void click_response_button_one_three_times_then_response_button_two_twice_then_dialogue_frame_exits() {
        clickTargetButtonXManyTimes(3, responseButtonOne);
        clickTargetButtonXManyTimes(2, responseButtonTwo);

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertFalse(dialogueSystem.isDialogueActive());
        assertFalse(dialogueSystem.getDialogueFrame().isShowing());
        assertEquals(1, currentDialogue.getDialogueID());
    }

    @Test
    public void click_one_response_from_every_dialogue_then_exit_frame_after_responding_to_dialogue_ID_four() {
        responseButtonOne.doClick();
        responseButtonTwo.doClick();
        responseButtonThree.doClick();
        clickTargetButtonXManyTimes(2, responseButtonOne);

        currentDialogue = dialogueSystem.getCurrentDialogue();
        assertFalse(dialogueSystem.isDialogueActive());
        assertFalse(dialogueSystem.getDialogueFrame().isShowing());
        assertEquals(1, currentDialogue.getDialogueID());
    }

    private void clickTargetButtonXManyTimes(int numberOfTimes, DialogueButton dialogueButton) {
        for (int index = 0; index < numberOfTimes; index++) {
            dialogueButton.doClick();
        }
    }


}
