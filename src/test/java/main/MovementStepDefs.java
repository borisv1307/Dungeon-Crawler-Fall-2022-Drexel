package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@Given("^the level design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves left$")
	public void the_player_moves_left() throws Throwable {
		gameEngine.keyLeft();
	}

	@When("^the player moves right$")
	public void the_player_moves_right() throws Throwable {
		gameEngine.keyRight();
	}

	@When("^the player moves up$")
	public void the_player_moves_up() throws Throwable {
		gameEngine.keyUp();
	}

	@When("^the player moves down$")
	public void the_player_moves_down() throws Throwable {
		gameEngine.keyDown();
	}

	@When("^the projectile moves left$")
	public void the_projectile_moves_left() throws Throwable {
		gameEngine.moveProjectileLeft();
	}

	@When("^the projectile moves right$")
	public void the_projectile_moves_right() throws Throwable {
		gameEngine.moveProjectileRight();
	}

	@When("^the projectile moves up$")
	public void the_projectile_moves_up() throws Throwable {
		gameEngine.moveProjectileUp();
	}

	@When("^the projectile moves down$")
	public void the_projectile_moves_down() throws Throwable {
		gameEngine.moveProjectileDown();
	}

	@Then("^the player is located at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_at(int playerX, int playerY) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY - COORDINATE_OFFSET));
	}

	@Then("^the projectile is located at \\((\\d+), (\\d+)\\)$")
	public void the_projectile_is_located_at(int projectileX, int projectileY) throws Throwable {
		assertThat(gameEngine.getProjectileXCoordinate(), equalTo(projectileX - COORDINATE_OFFSET));
		assertThat(gameEngine.getProjectileYCoordinate(), equalTo(projectileY - COORDINATE_OFFSET));
	}
}
