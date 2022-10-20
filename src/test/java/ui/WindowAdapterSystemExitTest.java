package ui;

import org.junit.Test;
import org.mockito.Mockito;

import engine.GameEngine;

public class WindowAdapterSystemExitTest {

	@Test
	public void window_closing_sets_exit_in_game() {
		GameEngine gameEngine = Mockito.mock(GameEngine.class);
		WindowAdapterSystemExit windowAdapterSystemExit = new WindowAdapterSystemExit(gameEngine);

		windowAdapterSystemExit.windowClosing(null);

		Mockito.verify(gameEngine).setExit(true);
	}

}
