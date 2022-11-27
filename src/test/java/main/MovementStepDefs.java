package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.mockito.Mockito;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import ui.ScorePanel;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@Given("^the level design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		ScorePanel scorePanel = Mockito.mock(ScorePanel.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), scorePanel);
	}

	@When("^the player moves left$")
	public void the_player_moves_left() {
		gameEngine.keyLeft();
	}

	@When("^the player moves right$")
	public void the_player_moves_right() {
		gameEngine.keyRight();
	}

	@When("^the player moves up$")
	public void the_player_moves_up() {
		gameEngine.keyUp();
	}

	@When("^the player moves down$")
	public void the_player_moves_down() {
		gameEngine.keyDown();
	}

	@Then("^the player is located at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_at(int playerX, int playerY) {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY - COORDINATE_OFFSET));
	}

	@When("^the player moves up twice$")
	public void the_player_moves_up_twice() {
		gameEngine.keyUp();
		gameEngine.keyUp();
	}

	@Then("^the player is then located at \\((\\d+), (\\d+)\\)$")
	public void thePlayerIsThenLocatedAt(int playerX, int playerY) {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY));
	}
}
