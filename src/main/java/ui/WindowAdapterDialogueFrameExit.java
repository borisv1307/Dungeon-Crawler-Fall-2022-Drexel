package ui;

import engine.GameEngine;

import java.awt.*;
import java.awt.event.WindowAdapter;

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
        boolean isEquals = false;
        WindowAdapterDialogueFrameExit comparisonAdapter = (WindowAdapterDialogueFrameExit) object;
        if (comparisonAdapter.getType().equals(comparisonAdapter.getType())) {
            isEquals = true;
        }
        return isEquals;
    }

    private String getType() {
        return type;
    }
}
