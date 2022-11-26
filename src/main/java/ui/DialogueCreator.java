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
    private final XMLParserWrapper xmlParserWrapper;
    private static final String FILE_NAME = "npc";
    String filePath = XML_LOCATION_PREFIX + FILE_NAME + XML_NAME_SUFFIX;

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
                String dialogue = cleanElementStringData(dialogueContent);

                List<Response> responses = createResponsesArray(nodeElement);

                targetList.add(new Dialogue(dialogueID, dialogue, responses));
            }
        }
    }

    private List<Response> createResponsesArray(Element nodeElement) {
        List<Response> responsesFromXML = new ArrayList<>();
        NodeList nodeList = nodeElement.getElementsByTagName("responses");
        Node responsesNode = nodeList.item(0);

        if (responsesNode.getNodeType() == Node.ELEMENT_NODE) {
            Element responsesElement = (Element) responsesNode;
            NodeList responses = responsesElement.getElementsByTagName("response");

            for (int index = 0; index < responses.getLength(); index++) {
                Node response = responses.item(index);
                Element responseElement = (Element) response;
                int targetIDForNextDialogue = Integer.parseInt(responseElement.getAttribute("target"));
                String currentResponse = cleanElementStringData(response.getTextContent());

                responsesFromXML.add(new Response(currentResponse, targetIDForNextDialogue));
            }
        }
        return responsesFromXML;
    }

    private String cleanElementStringData(String targetString) {
        return targetString.trim();
    }


}
