package ui;

import engine.GameEngine;
import org.junit.Test;
import org.mockito.Mockito;

public class WindowAdapterDialogueFrameExitTest {

    @Test
    public void window_closing_sets_dialogue_is_active_false() {
        GameEngine gameEngine = Mockito.mock(GameEngine.class);
        DialogueFrame dialogueFrame = Mockito.mock(DialogueFrame.class);
        WindowAdapterDialogueFrameExit windowAdapterDialogueFrameExit = new WindowAdapterDialogueFrameExit(gameEngine, dialogueFrame);

        windowAdapterDialogueFrameExit.windowClosing(null);

        Mockito.verify(gameEngine).setIsDialogueActive(false);
        Mockito.verify(dialogueFrame).dispose();
    }

}
