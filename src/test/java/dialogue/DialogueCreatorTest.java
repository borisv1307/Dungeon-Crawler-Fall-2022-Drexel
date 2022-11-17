package dialogue;

import Dialogue.Dialogue;
import Dialogue.DialogueCreator;
import org.junit.Before;
import org.junit.Test;
import wrappers.XMLParserWrapper;

import java.util.ArrayList;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.junit.Assert.assertEquals;

public class DialogueCreatorTest {
    private DialogueCreator dialogueCreator;
    XMLParserWrapper xmlParserWrapper;
    ArrayList<Dialogue> dialogues;

    @Before
    public void setUp() {
        xmlParserWrapper = new XMLParserWrapper();
        dialogueCreator = new DialogueCreator(xmlParserWrapper);
        try {
            dialogues = dialogueCreator.createDialogueList();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

    }

    @Test
    public void first_dialogue_id_is_one() {
        Dialogue actual = dialogues.get(0);
        assertEquals(1, actual.getDialogueID());
    }

    @Test
    public void second_dialogue_id_is_two() {
        Dialogue actual = dialogues.get(1);
        assertEquals(2, actual.getDialogueID());
    }

    @Test
    public void third_dialogue_id_is_three() {
        Dialogue actual = dialogues.get(2);
        assertEquals(3, actual.getDialogueID());
    }

    @Test
    public void first_dialogue_content_is_first_dialogue() {
        Dialogue actual = dialogues.get(0);
        assertEquals("Dialogue One", actual.getDialogueContent());
    }

    @Test
    public void second_dialogue_content_is_second_dialogue() {
        Dialogue actual = dialogues.get(1);
        assertEquals("Dialogue Two", actual.getDialogueContent());
    }

    @Test
    public void third_dialogue_content_is_second_dialogue() {
        Dialogue actual = dialogues.get(2);
        assertEquals("Dialogue Three", actual.getDialogueContent());
    }
}
