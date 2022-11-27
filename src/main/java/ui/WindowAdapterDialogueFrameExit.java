package ui;

import engine.GameEngine;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.Objects;

public class WindowAdapterDialogueFrameExit extends WindowAdapter {

    private final GameEngine gameEngine;
    private final Frame dialogueFrame;
    private final String type;

    public WindowAdapterDialogueFrameExit(GameEngine gameEngine, Frame dialogueFrame) {
        this.gameEngine = gameEngine;
        this.dialogueFrame = dialogueFrame;
        this.type = "Dialogue Frame Exit Adapter";
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        gameEngine.setIsDialogueActive(false);
        dialogueFrame.dispose();
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
        return Objects.hash(gameEngine, dialogueFrame);
    }

    private String getType() {
        return type;
    }
}
