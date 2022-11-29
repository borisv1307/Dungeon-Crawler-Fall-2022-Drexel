package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doReturn;

public class DialogueSystemUITest {
    DialogueSystem dialogueSystem;
    DialogueSystem mockDialogueSystem;
    Dialogue actual;
    Dialogue currentDialogue;
    Response playerResponse;
    LevelCreator levelCreator;
    GameEngine gameEngine;
    int actualTargetID;
    int numberOfConsecutiveEnterPresses = 0;
    private static final String FIRST_DIALOGUE_CONTENT = "Halt! Who goes there? Are ye a player or a bug?";
    private static final String FIRST_DIALOGUE_RESPONSE_ONE_CONTENT = "Can you help me find the mythical code?";
    private static final int EXIT_ID = -1;

    @Before
    public void setUp() {
        levelCreator = Mockito.mock(LevelCreator.class);
        dialogueSystem = new DialogueSystem();
        numberOfConsecutiveEnterPresses = 10;
        mockDialogueSystem = Mockito.mock(DialogueSystem.class);

        gameEngine = new GameEngine(levelCreator, mockDialogueSystem);
        doReturn(false).when(mockDialogueSystem).isDialogueActive();

    }

    @Test
    public void dialogue_system_has_dialogues_when_created() {
        assertNotEquals(0, dialogueSystem.getDialogues().size());
    }

    @Test
    public void dialogue_system_creates_dialogues_objects_with_content_and_response() {
        Dialogue firstDialogue = dialogueSystem.getCurrentDialogue();
        List<Response> firstDialogueResponses = firstDialogue.getResponses();
        Response firstResponseChoiceForDialogueOne = firstDialogueResponses.get(0);

        assertEquals(1, firstDialogue.getDialogueID());
        assertEquals(FIRST_DIALOGUE_CONTENT, firstDialogue.getDialogueContent());
        assertEquals(3, firstDialogueResponses.size());
        assertEquals(FIRST_DIALOGUE_RESPONSE_ONE_CONTENT, firstResponseChoiceForDialogueOne.getResponseText());
    }

    @Test
    public void dialogue_system_updates_updates_current_dialogue_to_target_ID() {
        dialogueSystem.updateDialogueFrame(4);

        actual = dialogueSystem.getCurrentDialogue();
        assertEquals(4, actual.getDialogueID());
    }

    @Test
    public void dialogue_system_resets_dialogue_to_ID_one_when_requested() {
        dialogueSystem.updateDialogueFrame(5);
        dialogueSystem.resetCurrentDialogueToIDOne();

        actual = dialogueSystem.getCurrentDialogue();
        assertEquals(1, actual.getDialogueID());
    }

    @Test
    public void system_reads_response_one_to_dialogue_one_then_dialogue_two_should_be_next_target() {
        dialogueSystem.setCurrentDialogueToTargetDialogue(1);
        currentDialogue = dialogueSystem.getCurrentDialogue();

        playerResponse = currentDialogue.getResponses().get(0);
        actualTargetID = playerResponse.getTarget();
        assertEquals(2, actualTargetID);
    }

    @Test
    public void system_reads_response_three_to_dialogue_three_then_dialogue_five_should_be_next_target() {
        dialogueSystem.setCurrentDialogueToTargetDialogue(3);
        currentDialogue = dialogueSystem.getCurrentDialogue();

        playerResponse = currentDialogue.getResponses().get(2);
        actualTargetID = playerResponse.getTarget();
        assertEquals(5, actualTargetID);
    }

    @Test
    public void system_reads_response_three_to_dialogue_one_then_next_dialogue_id_should_be_exit_ID() {
        dialogueSystem.setCurrentDialogueToTargetDialogue(1);
        currentDialogue = dialogueSystem.getCurrentDialogue();

        playerResponse = currentDialogue.getResponses().get(2);
        actualTargetID = playerResponse.getTarget();
        assertEquals(EXIT_ID, actualTargetID);
    }

    @Test
    public void player_initiates_dialog_and_then_presses_enter_ten_times_system_should_only_open_one_frame() {
        gameEngine.keyEnter();
        doReturn(true).when(mockDialogueSystem).isDialogueActive();

        pressEnterXTimes(numberOfConsecutiveEnterPresses, gameEngine);

        Mockito.verify(mockDialogueSystem, Mockito.times(numberOfConsecutiveEnterPresses + 1)).isDialogueActive();
        Mockito.verify(mockDialogueSystem, Mockito.times(1)).initiateDialogueFrame();
    }

    @Test
    public void player_initiates_dialog_and_then_presses_enter_fifty_times_system_should_only_open_one_frame() {
        numberOfConsecutiveEnterPresses = 50;
        gameEngine.keyEnter();

        doReturn(true).when(mockDialogueSystem).isDialogueActive();
        pressEnterXTimes(numberOfConsecutiveEnterPresses, gameEngine);

        Mockito.verify(mockDialogueSystem, Mockito.times(numberOfConsecutiveEnterPresses + 1)).isDialogueActive();
        Mockito.verify(mockDialogueSystem, Mockito.times(1)).initiateDialogueFrame();
    }

    private void pressEnterXTimes(int targetNumber, GameEngine gameEngine) {
        for (int index = 0; index < targetNumber; index++) {
            gameEngine.keyEnter();
        }

    }

}
