package player;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PlayerTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int EIGHT = 8;

    private static final int TEN = 10;
    private static final int TWELVE = 12;
    private static final int THIRTEEN = 13;
    private static final int FIFTY = 50;

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player();
        player.setPoint(THREE, FIVE);
    }

    @Test
    public void add_and_get_coordinates() {
        player.setPoint(ONE, ZERO);
        int actualX = player.getX();
        int actualY = player.getY();
        assertThat(actualX, equalTo(ONE));
        assertThat(actualY, equalTo(ZERO));
    }

    @Test
    public void change_and_get_health() {
        player.changeHealth(THREE);
        int actualHP = player.getHP();
        assertThat(actualHP, equalTo(THIRTEEN));
    }

    @Test
    public void block_healing_over_max_health() {
        for (int healCounter = ONE; healCounter <= TEN; healCounter++) {
            player.changeHealth(THIRTEEN);
        }
        int actualHP = player.getHP();
        assertThat(actualHP, equalTo(FIFTY));
    }

    @Test
    public void block_damage_reducing_player_health_under_zero() {
        for (int damageCounter = ONE; damageCounter <= TEN; damageCounter++) {
            player.changeHealth(THIRTEEN * -1);
        }
        int actualHP = player.getHP();
        assertThat(actualHP, equalTo(ZERO));
    }

    @Test
    public void set_and_get_regen() {
        player.setRegen(true, ONE);
        boolean actualRegenStatus = player.isRegenOn();
        int actualRegenRemaining = player.getRegenRemaining();
        assertThat(actualRegenStatus, equalTo(true));
        assertThat(actualRegenRemaining, equalTo(ONE));
    }

    @Test
    public void set_and_get_drain() {
        player.setDrain(true, ONE);
        boolean actualDrainStatus = player.isDrainOn();
        int actualDrainRemaining = player.getDrainRemaining();
        assertThat(actualDrainStatus, equalTo(true));
        assertThat(actualDrainRemaining, equalTo(ONE));
    }

    @Test
    public void move_and_get_updated_coordinates() {
        player.move(ZERO, ONE);
        int actualX = player.getX();
        int actualY = player.getY();
        assertThat(actualX, equalTo(THREE + ZERO));
        assertThat(actualY, equalTo(FIVE + ONE));
    }

    @Test
    public void regen_heal_when_player_moves() {
        player.setRegen(true, FIFTY);
        player.move(ONE, ZERO);
        int actualHP = player.getHP();
        assertThat(actualHP, equalTo(TWELVE));
    }

    @Test
    public void regen_decrements_when_player_moves() {
        player.setRegen(true, FIVE);
        player.move(ONE, ZERO);
        int actualRegenRemaining = player.getRegenRemaining();
        assertThat(actualRegenRemaining, equalTo(FIVE - ONE));
    }

    @Test
    public void drain_damages_when_player_moves() {
        player.setDrain(true, FIFTY);
        player.move(ONE, ZERO);
        int actualHP = player.getHP();
        assertThat(actualHP, equalTo(EIGHT));
    }

    @Test
    public void drain_decrements_when_player_moves() {
        player.setDrain(true, FIVE);
        player.move(ONE, ZERO);
        int actualDrainRemaining = player.getDrainRemaining();
        assertThat(actualDrainRemaining, equalTo(FIVE - ONE));
    }

    @Test
    public void regen_disables_when_regen_counter_reaches_zero() {
        player.setRegen(true, ONE);
        player.move(ZERO, ONE);
        boolean actualRegenStatus = player.isRegenOn();
        int actualRegenRemaining = player.getRegenRemaining();
        assertThat(actualRegenStatus, equalTo(false));
        assertThat(actualRegenRemaining, equalTo(ZERO));
    }

    @Test
    public void drain_disables_when_drain_counter_reaches_zero() {
        player.setDrain(true, ONE);
        player.move(ZERO, ONE);
        boolean actualDrainStatus = player.isDrainOn();
        int actualDrainRemaining = player.getDrainRemaining();
        assertThat(actualDrainStatus, equalTo(false));
        assertThat(actualDrainRemaining, equalTo(ZERO));
    }

    @Test
    public void player_does_not_move_when_health_is_zero() {
        for (int damageCounter = ONE; damageCounter <= TEN; damageCounter++) {
            player.changeHealth(THIRTEEN * -1);
        }
        player.move(ZERO, ONE);
        player.move(ONE, ZERO);
        int actualX = player.getX();
        int actualY = player.getY();
        assertThat(actualX, equalTo(THREE));
        assertThat(actualY, equalTo(FIVE));

    }

    @Test
    public void player_reset_health() {
        for (int damageCounter = ONE; damageCounter <= TEN; damageCounter++) {
            player.changeHealth(THIRTEEN * -1);
        }
        player.reset();
        assertThat(player.getHP(), equalTo(TEN));
    }

    @Test
    public void player_reset_regen() {
        player.setRegen(true, TWELVE);
        player.reset();
        assertThat(player.isRegenOn(), equalTo(false));
        assertThat(player.getRegenRemaining(), equalTo(ZERO));
    }

    @Test
    public void player_reset_drain() {
        player.setDrain(true, TWELVE);
        player.reset();
        assertThat(player.isDrainOn(), equalTo(false));
        assertThat(player.getDrainRemaining(), equalTo(ZERO));
    }

}
