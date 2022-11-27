package wrappers;

import entity.Enemy;

import java.util.Random;

public class EnemyRandomWrapper {

    public void generateIntValue(Enemy enemy) {
        Random random = new Random();
        int max = enemy.getLevel() * 10;
        int min = (int) (max * 0.8);

        enemy.setHealthPoint(random.nextInt(max - min) + min);
        enemy.setAttackPoint(random.nextInt(max - min) + min);

    }
}
