package engine;

import entity.Enemy;
import entity.Player;
import org.junit.Test;
import org.mockito.Mockito;
import wrappers.EnemyRandomWrapper;

public class CombatManagementTest {
    
    CombatManagement combatManagement;
    Enemy enemy;

    @Test
    public void attack_by(){
        combatManagement = Mockito.mock(CombatManagement.class);
        enemy = new Enemy(1);
        enemy.createEnemy(new EnemyRandomWrapper());
        enemy.attackedBy(combatManagement);
        Mockito.verify(combatManagement).attack(enemy);
    }

}
