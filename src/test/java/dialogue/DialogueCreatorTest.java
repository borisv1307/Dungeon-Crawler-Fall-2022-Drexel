package dialogue;

import org.junit.Before;
import org.junit.Test;
import ui.Dialogue;
import ui.DialogueCreator;
import wrappers.XMLParserWrapper;

import java.util.List;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.junit.Assert.assertEquals;

public class DialogueCreatorTest {
    private DialogueCreator dialogueCreator;
    private XMLParserWrapper xmlParserWrapper;
    private List<Dialogue> dialogues;

    private String[] responses;

    @Before
    public void setUp() {
        xmlParserWrapper = new XMLParserWrapper();
        dialogueCreator = new DialogueCreator(xmlParserWrapper);
        responses = new String[3];
        try {
            dialogues = dialogueCreator.createDialogueList();
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

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

    @Test
    public void first_dialogue_content_should_be_first_dialogue_element_content() {
        Dialogue actual = dialogues.get(0);
        assertEquals("Hello, Player what can I help you with?", actual.getDialogueContent());
    }

    @Test
    public void second_dialogue_content_should_be_second_dialogue_element_content() {
        Dialogue actual = dialogues.get(1);
        assertEquals("I can help you find the code, you just have to sniff it out!", actual.getDialogueContent());
    }

    @Test
    public void third_dialogue_content_should_be_third_dialogue_element_content() {
        Dialogue actual = dialogues.get(2);
        assertEquals("I am here to keep watch over this level, watch your step now.", actual.getDialogueContent());
    }

    @Test
    public void first_dialogue_responses_has_first_response() {
        Dialogue dialogue = dialogues.get(0);
        responses = dialogue.getResponses();
        String actual = responses[0];
        assertEquals("Can you help me find the code?", actual);
    }

    @Test
    public void first_dialogue_responses_has_second_response() {
        Dialogue dialogue = dialogues.get(0);
        responses = dialogue.getResponses();
        String actual = responses[1];
        assertEquals("What are you doing here?", actual);
    }

    @Test
    public void first_dialogue_responses_has_third_response() {
        Dialogue dialogue = dialogues.get(0);
        responses = dialogue.getResponses();
        String actual = responses[2];
        assertEquals("Actually, I do not need help. Goodbye!", actual);
    }
}
