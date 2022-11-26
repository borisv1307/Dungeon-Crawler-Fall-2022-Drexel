package ui;

import java.util.List;

public class Dialogue {

    private final int dialogueID;
    private final String dialogueContent;
    private final List<Response> responses;


    public Dialogue(int dialogueID, String dialogueContent, List<Response> responses) {
        this.dialogueID = dialogueID;
        this.dialogueContent = dialogueContent;
        this.responses = responses;
    }

    public String getDialogueContent() {
        return dialogueContent;
    }

    public int getDialogueID() {
        return dialogueID;
    }

    public List<Response> getResponses() {
        return responses;
    }
}
