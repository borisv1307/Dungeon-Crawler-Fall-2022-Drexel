package ui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import wrappers.XMLParserWrapper;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static values.TunableParameters.XML_LOCATION_PREFIX;
import static values.TunableParameters.XML_NAME_SUFFIX;

public class DialogueCreator {
    private static final Logger LOGGER = Logger.getLogger(DialogueCreator.class.getName());
    private XMLParserWrapper xmlParserWrapper;
    private String fileName = "npc";
    String filePath = XML_LOCATION_PREFIX + fileName + XML_NAME_SUFFIX;

    public DialogueCreator(XMLParserWrapper xmlParserWrapper) {
        this.xmlParserWrapper = xmlParserWrapper;
    }

    public List<Dialogue> createDialogueList() {
        List<Dialogue> dialogues = new ArrayList<>();
        try {
            populateListFromFile(dialogues);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
        return dialogues;
    }

    private void populateListFromFile(List<Dialogue> targetList) throws IOException, ParserConfigurationException, SAXException {
        Document document;
        document = xmlParserWrapper.parse(filePath);

        NodeList nodeList = document.getElementsByTagName("dialogue");

        assembleDialogueFromNodeList(targetList, nodeList);
    }

    private void assembleDialogueFromNodeList(List<Dialogue> targetList, NodeList nodeList) {
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node dialogueNode = nodeList.item(index);

            if (dialogueNode.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element) dialogueNode;

                int dialogueID = Integer.parseInt(nodeElement.getAttribute("id"));

                String dialogueContent = nodeElement.getElementsByTagName("content").item(0).getTextContent();

                String content = cleanElementStringData(dialogueContent);

                String[] responses = createResponseArray(nodeElement);

                targetList.add(new Dialogue(dialogueID, content, responses));
            }
        }
    }

    private String[] createResponseArray(Element nodeElement) {
        String[] responses = new String[3];
        String[] prefixes = {"first_", "second_", "third_"};
        String suffix = "response";

        for (int index = 0; index < prefixes.length; index++) {
            String elementName = prefixes[index] + suffix;
            responses[index] = cleanElementStringData(nodeElement.getElementsByTagName(elementName).item(0).getTextContent());
        }
        return responses;
    }
    
    private String cleanElementStringData(String targetString) {
        return targetString.trim();
    }


}
