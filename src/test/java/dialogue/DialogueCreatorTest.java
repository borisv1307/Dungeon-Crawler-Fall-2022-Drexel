package dialogue;

import org.junit.Before;
import org.junit.Test;
import parser.LevelCreator;
import ui.Dialogue;
import ui.DialogueCreator;
import ui.Response;
import wrappers.XMLParserWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class DialogueCreatorTest {
    private static final Logger LOGGER = Logger.getLogger(LevelCreator.class.getName());
    private DialogueCreator dialogueCreator;
    private XMLParserWrapper xmlParserWrapper;
    private List<Dialogue> dialogues;
    private List<Response> responsesToCurrentDialogue;
    private Dialogue dialogueActual;

    @Before
    public void setUp() {
        xmlParserWrapper = new XMLParserWrapper();
        dialogueCreator = new DialogueCreator(xmlParserWrapper);
        responsesToCurrentDialogue = new ArrayList<>();
        try {
            dialogues = dialogueCreator.createDialogueList();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }

    @Test
    public void XMLParser_creates_five_dialogues() {
        assertEquals(5, dialogues.size());
    }

    @Test
    public void first_dialogue_id_should_be_one() {
        dialogueActual = getDialogueByID(1);
        assertEquals(1, dialogueActual.getDialogueID());
    }

    @Test
    public void second_dialogue_id_should_be_two() {
        dialogueActual = getDialogueByID(2);
        assertEquals(2, dialogueActual.getDialogueID());
    }

    @Test
    public void third_dialogue_id_should_be_three() {
        dialogueActual = getDialogueByID(3);
        assertEquals(3, dialogueActual.getDialogueID());
    }

    @Test
    public void first_dialogue_has_three_responses() {
        dialogueActual = getDialogueByID(1);
        assertEquals(3, dialogueActual.getResponses().size());
    }

    @Test
    public void first_dialogue_has_correct_responses_content() {
        Dialogue firstDialogue = getDialogueByID(1);
        responsesToCurrentDialogue = firstDialogue.getResponses();
        assertEquals("Can you help me find the mythical code?", responsesToCurrentDialogue.get(0).getResponseText());
        assertEquals("What are you doing here?", responsesToCurrentDialogue.get(1).getResponseText());
        assertEquals("Actually, I do not need help. Goodbye!", responsesToCurrentDialogue.get(2).getResponseText());
    }

    @Test
    public void second_dialogue_has_only_two_responses() {
        dialogueActual = getDialogueByID(2);
        responsesToCurrentDialogue = dialogueActual.getResponses();
        assertEquals(2, responsesToCurrentDialogue.size());
    }

    @Test
    public void second_dialogue_has_correct_responses_content() {
        Dialogue firstDialogue = getDialogueByID(2);
        responsesToCurrentDialogue = firstDialogue.getResponses();
        assertEquals("I'm sorry I wasn't listening, can we start over?", responsesToCurrentDialogue.get(0).getResponseText());
        assertEquals("Great, but what are you doing here, NPC?", responsesToCurrentDialogue.get(1).getResponseText());
    }

    private Dialogue getDialogueByID(int targetID) {
        Dialogue targetDialogue = new Dialogue(100, "placeholder", new ArrayList<>());
        for (Dialogue dialogue : dialogues) {
            if (dialogue.getDialogueID() == targetID) {
                targetDialogue = dialogue;
            }
        }
        return targetDialogue;
    }


}
