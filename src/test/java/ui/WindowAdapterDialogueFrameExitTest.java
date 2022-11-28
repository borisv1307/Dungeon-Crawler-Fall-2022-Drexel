package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class WindowAdapterDialogueFrameExitTest {
    DialogueSystem dialogueSystem;
    WindowAdapterDialogueFrameExit windowAdapterDialogueFrameExit;

    @Before
    public void setUp() {
        dialogueSystem = Mockito.mock(DialogueSystem.class);

        windowAdapterDialogueFrameExit = new WindowAdapterDialogueFrameExit(dialogueSystem);

        windowAdapterDialogueFrameExit.windowClosing(null);
    }

    @Test
    public void dialogue_frame_closing_sets_terminates_dialogue_system_and_sets_is_active_to_false() {
        Mockito.verify(dialogueSystem).setIsDialogueActive(false);
        Mockito.verify(dialogueSystem).terminate();
    }

    @Test
    public void dialogue_frame_closing_resets_current_dialogue_to_dialogue_ID_one() {
        Mockito.verify(dialogueSystem).resetCurrentDialogueToIDOne();
    }

}
