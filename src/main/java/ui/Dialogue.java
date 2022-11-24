package ui;

public class Dialogue {

    private final int dialogueID;
    private final String dialogueContent;
    private Response[] responses;


    Dialogue(int dialogueID, String dialogueContent, Response[] responses) {
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

    public Response[] getResponses() {
        return responses;
    }
}
