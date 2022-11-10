package values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static values.TunableParameters.PLAYER_SPEED;

public class TestingTunableParameters {
    public static final String FILE_LOCATION_PREFIX = "src/test/resources/levels/";

    @Test
    public void player_speed_is_one() {
        assertEquals(1, PLAYER_SPEED);
    }
}
