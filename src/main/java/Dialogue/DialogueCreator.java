package Dialogue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import wrappers.XMLParserWrapper;

import java.util.ArrayList;
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

    public ArrayList<Dialogue> createDialogueList() {
        ArrayList<Dialogue> dialogues = new ArrayList<>();
        Document document;
        try {
            //TODO //Refactor into helper methods. Move the try catch into helper.

            document = xmlParserWrapper.parse(filePath);
            NodeList nodeList = document.getElementsByTagName("dialogue");
            for (int index = 0; index < nodeList.getLength(); index++) {
                Node dialogueNode = nodeList.item(index);

                if (dialogueNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element nodeElement = (Element) dialogueNode;
                    int dialogueID = Integer.parseInt(nodeElement.getAttribute("id"));

                    String elementContent = nodeElement.getElementsByTagName("content").item(0).getTextContent();
                    String dialogueContent = cleanElementStringData(elementContent);

                    dialogues.add(new Dialogue(dialogueID, dialogueContent));
                }

            }
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }

        return dialogues;
    }

    private String cleanElementStringData(String targetString) {
        return targetString.trim();
    }


}
