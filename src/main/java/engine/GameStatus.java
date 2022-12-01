package engine;

public interface GameStatus {
    String respawn = "You have respawned.";
    String enemyDefeated = "%s has been defeated.";
    String playerDefeated = "You have been defeated.";
    String damageToEnemy = "You attack the %s for %d.  Its armor deflects %d point of damage.";
}
