package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DialogueFrameGUITest {
    DialogueFrame dialogueFrame;
    GameEngine gameEngine;
    GridBagConstraints gridBagConstraints;
    int height;
    int width;

    DialoguePanel dialoguePanel;

    @Before
    public void setUp() {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);

        dialoguePanel = Mockito.mock(DialoguePanel.class);
        gameEngine = new GameEngine(levelCreator);
        dialogueFrame = Mockito.mock(DialogueFrame.class);

        width = TunableParameters.SCREEN_WIDTH;
        height = TunableParameters.SCREEN_HEIGHT;

        gridBagConstraints = Mockito.mock(GridBagConstraints.class);
    }

    @Test
    public void constructor() {
        final String jTextAreaLabel = "default text area";

        dialogueFrame = new DialogueFrame(dialoguePanel, gameEngine) {
            @Override
            public Component add(Component comp) {
                assertThat((Panel) comp, equalTo(dialoguePanel));
                return dialoguePanel;
            }

            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(true));
            }
        };

        WindowAdapterDialogueFrameExit windowAdapter = new WindowAdapterDialogueFrameExit(gameEngine, dialogueFrame);

        final JTextArea textArea = dialogueFrame.getDialogueTextArea();
        final Font textAreaFont = textArea.getFont();

        assertThat(dialogueFrame.isResizable(), equalTo(false));
        System.out.println(dialogueFrame.getWindowListeners()[0]);
        assertEquals(true, dialogueFrame.getWindowListeners()[0].equals(windowAdapter));

        assertEquals(dialogueFrame.getLayout().toString(), new GridBagLayout().toString());

        assertEquals(jTextAreaLabel, textArea.getText());
        assertFalse(textArea.isEditable());
        assertEquals(Font.ITALIC, textAreaFont.getStyle());
        assertEquals(16, textAreaFont.getSize());
        assertEquals("Text Area Font", textAreaFont.getName());

        Mockito.verify(dialoguePanel).setPreferredSize(new Dimension(width, height));
        Mockito.verify(dialoguePanel).setBackground(Color.GRAY);
    }

    @Test
    public void dialogue_frame_closes_when_user_clicks_exit_dialogue_frame_exit() {
        final DialogueFrame frameActual = new DialogueFrame(new DialoguePanel(), gameEngine);
        frameActual.dispatchEvent(new WindowEvent(frameActual, WindowEvent.WINDOW_CLOSING));
        assertFalse(frameActual.isShowing());
    }

    @Test
    public void dialogue_panel_has_three_buttons() {
        final DialogueButton actual = Mockito.mock(DialogueButton.class);
        Mockito.when(actual.getName()).thenReturn("default dialogue button");
        dialoguePanel = new DialoguePanel() {
            @Override
            public Component add(Component comp) {
                DialogueButton currentButton = (DialogueButton) comp;
                assertThat(currentButton.getName(), equalTo(actual.getName()));
                return actual;
            }
        };
    }


}
