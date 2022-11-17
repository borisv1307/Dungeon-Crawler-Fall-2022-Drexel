import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import wrappers.XMLParserWrapper;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static values.TunableParameters.XML_LOCATION_PREFIX;
import static values.TunableParameters.XML_NAME_SUFFIX;

public class XMLParserWrapperTest {

    String xmlName;
    String xmlNamePrefix;
    String xmlNameSuffix;
    
    private XMLParserWrapper xmlParserWrapper;

    @Before
    public void setUp() {
        xmlParserWrapper = new XMLParserWrapper();
        xmlName = "npc";
        xmlNamePrefix = XML_LOCATION_PREFIX;
        xmlNameSuffix = XML_NAME_SUFFIX;
    }

    @Test
    public void parse_dialogue_file_to_document() throws IOException, ParserConfigurationException, SAXException {
        String fileName = xmlNamePrefix + xmlName + xmlNameSuffix;
        System.out.print(fileName);
        Document actual = xmlParserWrapper.parseXML(fileName);
        assertEquals("dialogues", actual.getDocumentElement().getNodeName());
    }
}
