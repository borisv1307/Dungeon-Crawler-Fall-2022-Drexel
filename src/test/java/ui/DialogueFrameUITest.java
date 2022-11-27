package ui;

import engine.GameEngine;
import org.junit.Before;
import org.mockito.Mockito;
import parser.LevelCreator;

public class DialogueFrameUITest {

    GameEngine gameEngine;
    DialogueFrame dialogueFrame;

    @Before
    public void setUp() {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        dialogueFrame = new DialogueFrame(new DialoguePanel(), gameEngine);
        dialogueFrame = Mockito.mock(DialogueFrame.class);
    }


}

