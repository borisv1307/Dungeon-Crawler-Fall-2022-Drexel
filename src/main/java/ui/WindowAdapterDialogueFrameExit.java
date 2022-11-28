package ui;

import java.awt.event.WindowAdapter;
import java.util.Objects;

public class WindowAdapterDialogueFrameExit extends WindowAdapter {

    private final DialogueSystem dialogueSystem = DialogueSystem.getInstance();
    private String type;

    public WindowAdapterDialogueFrameExit() {
        this.type = "Dialogue Frame Exit Adapter";
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        dialogueSystem.setIsDialogueActive(false);
        dialogueSystem.terminate();
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        WindowAdapterDialogueFrameExit windowAdapter = (WindowAdapterDialogueFrameExit) object;
        return type.equals(windowAdapter.getType());

    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    private String getType() {
        return type;
    }

}
