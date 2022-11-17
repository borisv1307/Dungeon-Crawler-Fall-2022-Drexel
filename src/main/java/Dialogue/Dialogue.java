package Dialogue;

public class Dialogue {

    private int dialogueID;
    private String dialogueContent;
    private String[] responses;

    Dialogue(int dialogueID, String dialogueContent, String[] responses) {
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
    
    public String[] getResponses() {
        return responses;
    }
}
