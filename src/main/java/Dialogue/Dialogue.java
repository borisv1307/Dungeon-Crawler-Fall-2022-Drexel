package Dialogue;

public class Dialogue {

    private int dialogueID;
    private String dialogueContent;

    Dialogue(int dialogueID, String dialogueContent) {
        this.dialogueID = dialogueID;
        this.dialogueContent = dialogueContent;
    }

    public String getDialogueContent() {
        return dialogueContent;
    }

    public int getDialogueID() {
        return dialogueID;
    }


}
