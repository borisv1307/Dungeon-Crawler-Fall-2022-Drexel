package Dialogue;

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

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static values.TunableParameters.XML_LOCATION_PREFIX;
import static values.TunableParameters.XML_NAME_SUFFIX;

public class DialogueCreator {
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

        //TODO refactor this
        String firstResponse = nodeElement.getElementsByTagName("first_response").item(0).getTextContent();
        responses[0] = cleanElementStringData(firstResponse);

        String secondResponse = nodeElement.getElementsByTagName("second_response").item(0).getTextContent();
        responses[1] = cleanElementStringData(secondResponse);

        String thirdResponse = nodeElement.getElementsByTagName("third_response").item(0).getTextContent();
        responses[2] = cleanElementStringData(thirdResponse);


        return responses;
    }


    private String cleanElementStringData(String targetString) {
        return targetString.trim();
    }


}
