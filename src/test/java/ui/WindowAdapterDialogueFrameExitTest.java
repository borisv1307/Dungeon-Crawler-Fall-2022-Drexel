package ui;

import org.junit.Test;

import java.awt.event.WindowEvent;

import static org.junit.Assert.assertFalse;

public class WindowAdapterDialogueFrameExitTest {

    @Test
    public void window_closing_sets_dialogue_system_is_dialogue_active_false() {
        final DialogueSystem dialogueSystem = DialogueSystem.getInstance();
        dialogueSystem.initiateDialogueFrame();

        WindowAdapterDialogueFrameExit windowAdapterDialogueFrameExit = new WindowAdapterDialogueFrameExit();
        windowAdapterDialogueFrameExit.windowClosing(new WindowEvent(dialogueSystem.dialogueFrame, WindowEvent.WINDOW_CLOSING));

        assertFalse(dialogueSystem.isDialogueActive);

    }

}
