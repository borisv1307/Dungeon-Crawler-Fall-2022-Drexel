package engine;

import entities.Enemy;
import entities.Player;
import entities.Slime;
import org.junit.Before;
import org.junit.Test;
import wrappers.RandomizerWrapper;
import wrappers.SystemWrapper;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CombatEngineTest {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    CombatEngine combatEngine;

    @Before
    public void setUp() throws Exception {
        RandomizerWrapper randomizerWrapper = new RandomizerWrapper(new SystemWrapper());
        combatEngine = new CombatEngine(randomizerWrapper);
    }

    @Test
    public void player_respawns_on_death() {
        Player initialPlayer = createPlayer();
        initialPlayer.move(ZERO, ONE);
        int previousX = (int) initialPlayer.getX();
        int previousY = (int) initialPlayer.getY();
        Player respawnedPlayer = combatEngine.killPlayer(initialPlayer);
        int actualX = (int) respawnedPlayer.getX();
        int actualY = (int) respawnedPlayer.getY();
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ZERO));
        assertFalse(actualX == previousX && actualY == previousY);
        assertEquals(false, initialPlayer.equals(respawnedPlayer));
    }

    @Test
    public void enemy_dies_updates_status() {
        Enemy enemy = createSlime();
        combatEngine.killEnemy(enemy);
        String expected = String.format(GameStatus.ENEMY_DEFEATED, "Slime");
        assertEquals(expected, combatEngine.getGameStatusString());
    }

    @Test
    public void spawn_new_enemy_on_defeat() {
        Enemy enemy = createSlime();
        Player player = createPlayer();
        CombatObject combatObject = new CombatObject(player, enemy);
        CombatObject returnObject = combatEngine.doCombat(combatObject);
        Enemy newEnemy = returnObject.enemy;
        assertEquals(false, enemy.equals(newEnemy));
    }

    @Test
    public void player_dies_updates_status() {
        Player player = createPlayer();
        combatEngine.killPlayer(player);
        assertEquals(GameStatus.PLAYER_DEFEATED, combatEngine.getGameStatusString());
    }

    private Player createPlayer() {
        return new Player(ZERO, ZERO);
    }

    private Slime createSlime() {
        return new Slime(ZERO, TWO);
    }
}
