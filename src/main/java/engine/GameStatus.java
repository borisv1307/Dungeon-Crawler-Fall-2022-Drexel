package engine;

public final class GameStatus {
    public static final String PLAYER_RESPAWNED = "You have respawned.";
    public static final String ENEMY_DEFEATED = "%s has been defeated.";
    public static final String PLAYER_DEFEATED = "You have been defeated.";
    public static final String DAMAGE_TO_ENEMY = "You attack the %s for %d.  Its armor deflects %d point of damage.";

    private GameStatus() {
        throw new IllegalStateException("Utility class");
    }
}
