package ui;

import org.junit.Test;
import org.mockito.Mockito;
import values.TunableParameters;

import java.awt.*;
import java.awt.event.WindowListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;

public class DialogueFrameUITest {
    DialogueFrame dialogueFrame;

    @SuppressWarnings("serial")
    @Test
    public void constructor() throws Exception {
        int width = TunableParameters.SCREEN_WIDTH;
        int height = TunableParameters.SCREEN_HEIGHT;
        final DialoguePanel dialoguePanel = Mockito.mock(DialoguePanel.class);

        WindowAdapterSystemExit windowAdapter = Mockito.mock(WindowAdapterSystemExit.class);

        dialogueFrame = new DialogueFrame(dialoguePanel, windowAdapter) {
            @Override
            public Component add(Component comp) {
                assertThat((DialoguePanel) comp, equalTo(dialoguePanel));
                return dialoguePanel;
            }

            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(false));
            }
        };
        Mockito.verify(dialoguePanel).setPreferredSize(new Dimension(width, height));
        assertThat(dialogueFrame.isResizable(), equalTo(false));
        assertThat(dialogueFrame.getWindowListeners(), arrayContaining((WindowListener) windowAdapter));
    }
}
