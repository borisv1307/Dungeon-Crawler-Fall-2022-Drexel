package engine;

import entity.Enemy;
import entity.Player;
import parser.LevelCreator;

public class CombatManagement {
   private GameEngine gameEngine;

   private LevelCreator levelCreator;

    private Player player;

    private Enemy enemy;

    private ActionHandler actionHandler;


    public CombatManagement(GameEngine gameEngine, LevelCreator levelCreator, Player player, Enemy enemy, ActionHandler actionHandler){
        this.gameEngine = gameEngine;
        this.levelCreator = levelCreator;
        this.player = player;
        this.enemy = enemy;
        this.actionHandler = actionHandler;
    }
    
    public void attack(Enemy enemy){
        if(actionHandler.playerIsNextToEnemy()){
            int playerCurrentHealth = player.getCurrentHealthPoint();
            int enemyCurrentHealth = enemy.getCurrentHealthPoint();

            while (playerCurrentHealth != 0 && enemyCurrentHealth != 0) {
                playerCurrentHealth -= enemy.getAttackPoint();
                enemyCurrentHealth -= player.getAttackPoint();
            }

            if (playerCurrentHealth <= 0) {
                System.out.println("You lost");
            } else {
                win();
                System.out.println("You win");
            }
        }

    }

     public void win() {
        player.levelUp();
        levelCreator.createLevel(gameEngine, 1);
    }
}
