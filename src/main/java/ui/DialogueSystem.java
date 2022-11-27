package ui;

import wrappers.XMLParserWrapper;

import java.util.List;

public class DialogueSystem {
    private final DialogueCreator dialogueCreator;
    private final List<Dialogue> dialogues;
    
    public DialogueSystem() {
        dialogueCreator = new DialogueCreator(new XMLParserWrapper());
        dialogues = dialogueCreator.createDialogueList();
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }
}
