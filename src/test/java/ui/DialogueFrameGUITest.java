package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import values.TunableParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static values.TunableParameters.DIALOGUE_BUTTON_HEIGHT;
import static values.TunableParameters.DIALOGUE_BUTTON_WIDTH;

public class DialogueFrameGUITest {
    DialogueFrame dialogueFrame;
    int height;
    int width;
    DialoguePanel dialoguePanel;
    DialogueSystem dialogueSystem;
    DialogueButton dialogueButton;
    DialogueButton buttonOne;
    DialogueButton buttonTwo;
    DialogueButton buttonThree;
    JTextArea jTextArea;
    DialoguePanel mockDialoguePanel;
    List<DialogueButton> buttons;
    Dimension dimension;

    private static final Dimension EXPECTED_BUTTON_DIMENSIONS = new Dimension(DIALOGUE_BUTTON_WIDTH, DIALOGUE_BUTTON_HEIGHT);

    @Before

    public void setUp() {
        dialoguePanel = new DialoguePanel();
        mockDialoguePanel = Mockito.mock(DialoguePanel.class);
        dialogueSystem = Mockito.mock(DialogueSystem.class);
        dialogueButton = new DialogueButton();

        width = TunableParameters.SCREEN_WIDTH;
        height = TunableParameters.SCREEN_HEIGHT;

        dialogueFrame = new DialogueFrame(new DialoguePanel(), dialogueSystem);
        buttons = dialogueFrame.getButtons();

        buttonOne = buttons.get(0);
        buttonTwo = buttons.get(1);
        buttonThree = buttons.get(2);

        jTextArea = dialogueFrame.getDialogueTextArea();
    }

    @Test
    public void constructor() {
        dialogueFrame = new DialogueFrame(mockDialoguePanel, dialogueSystem) {
            @Override
            public Component add(Component comp) {
                assertThat((Panel) comp, equalTo(mockDialoguePanel));
                return mockDialoguePanel;
            }

            @Override
            public void setVisible(boolean b) {
                assertThat(b, equalTo(false));
            }
        };

        WindowAdapterDialogueFrameExit windowAdapter = new WindowAdapterDialogueFrameExit(dialogueSystem);

        assertThat(dialogueFrame.isResizable(), equalTo(false));
        assertThat(dialogueFrame.getWindowListeners(), arrayContaining((WindowListener) windowAdapter));

        assertEquals(dialogueFrame.getLayout().toString(), new GridBagLayout().toString());
        Mockito.verify(mockDialoguePanel).setPreferredSize(new Dimension(width, height));
        Mockito.verify(mockDialoguePanel).setBackground(Color.GRAY);
    }

    @Test
    public void dialogue_panel_layout_manager_is_flow_layout() {
        dialoguePanel = new DialoguePanel();
        final LayoutManager actual = dialoguePanel.getLayout();
        assertEquals(actual.toString(), new FlowLayout().toString());
    }

    @Test
    public void dialogue_panel_only_has_four_components_on_creation() {
        assertEquals(4, dialoguePanel.getComponentCount());
    }

    @Test
    public void dialogue_panel_has_has_three_components_on_creation() {
        assertEquals(3, dialoguePanel.getDialoguePanelButtons().size());
    }

    @Test
    public void dialogue_panel_components_should_either_equal_dialogue_button_or_j_text_field() {
        dialoguePanel = new DialoguePanel() {
            @Override
            public Component add(Component comp) {
                DialogueButton currentButton = (DialogueButton) comp;
                assertEquals(currentButton, dialogueButton);
                return dialogueButton;
            }

            @Override
            public void add(Component comp, Object constraint) {
                JTextArea currentTextArea = (JTextArea) comp;
                assertThat(currentTextArea.getClass(), equalTo(JTextArea.class));
            }
        };
    }

    @Test
    public void dialogue_panel_button_one_is_created_with_correct_dimension() {
        dimension = createDimensionFromWidthAndHeight(buttonOne.getWidth(), buttonOne.getHeight());
        assertEquals(EXPECTED_BUTTON_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_panel_button_two_is_created_with_correct_dimension() {
        dimension = createDimensionFromWidthAndHeight(buttonTwo.getWidth(), buttonTwo.getHeight());
        assertEquals(EXPECTED_BUTTON_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_panel_button_three_is_created_with_correct_dimension() {
        dimension = createDimensionFromWidthAndHeight(buttonThree.getWidth(), buttonThree.getHeight());
        assertEquals(EXPECTED_BUTTON_DIMENSIONS, dimension);
    }

    @Test
    public void dialogue_panel_text_area_has_correct_font() {
        final JTextArea textArea = dialogueFrame.getDialogueTextArea();
        final Font textAreaFont = textArea.getFont();

        assertFalse(textArea.isEditable());
        assertEquals(Font.ITALIC, textAreaFont.getStyle());
        assertEquals(16, textAreaFont.getSize());
        assertEquals("Text Area Font", textAreaFont.getName());
    }

    @Test
    public void dialogue_frame_closes_when_user_clicks_dialogue_frame_system_exit() {
        final DialogueFrame frameActual = new DialogueFrame(new DialoguePanel(), dialogueSystem);
        frameActual.dispatchEvent(new WindowEvent(frameActual, WindowEvent.WINDOW_CLOSING));
        assertFalse(frameActual.isShowing());
    }

    private Dimension createDimensionFromWidthAndHeight(int width, int height) {
        return new Dimension(width, height);
    }


}
