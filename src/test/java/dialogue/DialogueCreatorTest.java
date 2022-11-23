package dialogue;

import org.junit.Before;
import org.junit.Test;
import parser.LevelCreator;
import ui.Dialogue;
import ui.DialogueCreator;
import wrappers.XMLParserWrapper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class DialogueCreatorTest {
    private static final Logger LOGGER = Logger.getLogger(LevelCreator.class.getName());
    private Dialogue firstDialogue;
    private DialogueCreator dialogueCreator;
    private XMLParserWrapper xmlParserWrapper;
    private List<Dialogue> dialogues;
    private String[] responsesToFirstDialogue;

    @Before
    public void setUp() {
        xmlParserWrapper = new XMLParserWrapper();
        dialogueCreator = new DialogueCreator(xmlParserWrapper);
        responsesToFirstDialogue = new String[3];
        try {
            dialogues = dialogueCreator.createDialogueList();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
        firstDialogue = dialogues.get(0);
        responsesToFirstDialogue = firstDialogue.getResponses();

    }

    @Test
    public void first_dialogue_id_should_be_one() {
        Dialogue actual = dialogues.get(0);
        assertEquals(1, actual.getDialogueID());
    }

    @Test
    public void second_dialogue_id_should_be_two() {
        Dialogue actual = dialogues.get(1);
        assertEquals(2, actual.getDialogueID());
    }

    @Test
    public void third_dialogue_id_should_be_three() {
        Dialogue actual = dialogues.get(2);
        assertEquals(3, actual.getDialogueID());
    }

}
