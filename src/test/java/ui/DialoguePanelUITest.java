package ui;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;

import static org.junit.Assert.assertEquals;

public class DialoguePanelUITest {
    GameEngine gameEngine;
    DialoguePanel dialoguePanel;
    DialogueFrame dialogueFrame;

    @Before
    public void setUp() {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        dialoguePanel = Mockito.mock(DialoguePanel.class);
        gameEngine = new GameEngine(levelCreator);
        dialogueFrame = Mockito.mock(DialogueFrame.class);
    }

    @Test
    public void click_response_one_then_dialogue_should_be_ID_two() {
        final DialogueFrame frameActual = new DialogueFrame(new DialoguePanel(), gameEngine);
        frameActual.updateDialogueFrame(1);

        final DialoguePanel panelActual = (DialoguePanel) frameActual.getComponent(0);
        final DialogueButton buttonActual = panelActual.getDialogueButton(0);
        buttonActual.doClick();

        Dialogue actual = frameActual.getCurrentDialogue();
        assertEquals(2, actual.getDialogueID());
    }
}
