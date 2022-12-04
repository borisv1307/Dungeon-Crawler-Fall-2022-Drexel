package engine;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entity.Enemy;
import entity.Player;
import parser.LevelCreator;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import static junit.framework.TestCase.assertSame;

public class CombatManagementStepDefs {

    GameEngine gameEngine;
    LevelCreator levelCreator;
    Player player;
    CombatManagement combatManagement;

    Enemy enemy;

    @Given("^player fight the enemy$")
    public void win() {
        levelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper());
        gameEngine = new GameEngine(levelCreator);
        player = new Player();
        enemy = new Enemy(1);
        combatManagement = new CombatManagement(gameEngine, levelCreator, player);
    }

    @When("^the enemy die first$")
    public void the_player_attack_first() {
        enemy.setCurrentHealthPoint(1);
        combatManagement.attack(enemy);
    }

    @When("^the player die first$")
    public void the_enemy_health_is_greater_than_player_attack() {
        player.setCurrentHealthPoint(1);
        player.setAttackPoint(1);
        combatManagement.attack(enemy);
    }

    @Then("^the player win and level up$")
    public void the_player_win() {
        assertSame(2, player.getLevel());
        assertSame(20, player.getMaxHealthPoint());
        assertSame(14, player.getAttackPoint());

    }

    @Then("^the player lose$")
    public void the_player_lose() {
        assertSame(1, player.getLevel());
        assertSame(10, player.getAttackPoint());
        assertSame(10, player.getMaxHealthPoint());
        assertSame(10, player.getCurrentHealthPoint());

    }
}
