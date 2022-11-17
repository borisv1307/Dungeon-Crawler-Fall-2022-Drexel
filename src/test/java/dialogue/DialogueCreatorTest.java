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
    public void first_dialogue_content_is_first_dialogue_content() {
        Dialogue actual = dialogues.get(0);
        assertEquals("Dialogue One", actual.getDialogueContent());
    }


}
