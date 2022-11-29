package wrappers;

import entity.Enemy;

import java.security.SecureRandom;
import java.util.Random;

public class EnemyRandomWrapper {
    private Random random;

    public EnemyRandomWrapper() {
        random = new SecureRandom();
    }
    public void generateIntValue(Enemy enemy) {
        int max = enemy.getLevel() * 10;
        int min = (int) (max * 0.8);

        enemy.setHealthPoint(random.nextInt(max - min) + min);
        enemy.setAttackPoint(random.nextInt(max - min) + min);

    }
}