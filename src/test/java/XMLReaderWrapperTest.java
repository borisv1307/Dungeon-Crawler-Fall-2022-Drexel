import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import wrappers.XMLReaderWrapper;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XMLReaderWrapperTest {


    private static final String FILE_LOCATION = "src/test/resources/dialogues/";
    private static final String FILE_NAME = "npc.xml";

    private XMLReaderWrapper xmlReaderWrapper;

    @Before
    public void setUp() {
        xmlReaderWrapper = new XMLReaderWrapper();
    }

    @Test
    public void parse_dialogue_file_to_document() throws IOException, ParserConfigurationException, SAXException {
        String file = FILE_LOCATION + FILE_NAME;
        Document actual = xmlReaderWrapper.parseXML(file);
        assertEquals("dialogues", actual.getDocumentElement().getNodeName());
    }
}
