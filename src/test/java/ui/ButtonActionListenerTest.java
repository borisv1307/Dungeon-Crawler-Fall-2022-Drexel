package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.event.ActionEvent;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;

public class ButtonActionListenerTest {

    DialogueFrame dialogueFrame;
    DialogueButton dialogueButton;
    ButtonClickActionListener buttonClickActionListener;
    ActionEvent actionEvent;

    @Before
    public void setUp() {
        dialogueFrame = Mockito.mock(DialogueFrame.class);
        buttonClickActionListener = new ButtonClickActionListener(dialogueFrame);

        dialogueButton = new DialogueButton("test");

        actionEvent = Mockito.mock(ActionEvent.class);
        Mockito.when(actionEvent.getSource()).thenReturn(dialogueButton);
    }


    @Test
    public void button_listener_updates_dialogue_frame_when_user_clicks_a_response_button() {
        Mockito.when(dialogueFrame.readPlayerResponseToFindNextDialogueID(anyString())).thenReturn(2);
        buttonClickActionListener.actionPerformed(actionEvent);

        Mockito.verify(dialogueFrame).readPlayerResponseToFindNextDialogueID(anyString());
        Mockito.verify(dialogueFrame).updateDialogueFrame(anyInt());
    }

    @Test
    public void button_listener_closes_dialogue_frame_if_user_chooses_a_response_with_ID_negative_one() {
        Mockito.when(dialogueFrame.readPlayerResponseToFindNextDialogueID(anyString())).thenReturn(-1);

        buttonClickActionListener.actionPerformed(actionEvent);

        Mockito.verify(dialogueFrame).dispose();
        Mockito.verify(dialogueFrame, never()).updateDialogueFrame(-1);
    }

}
