package main;
import values.TunableParameters;
import wrappers.RandomWrapper;
import java.util.ArrayList;
import java.util.List;

public class EnemyHandler {
    private static final List<Enemy> enemies = new ArrayList<>();
    private double chanceOfSpawn = TunableParameters.INITIAL_CHANCE_OF_SPAWN;


    public Enemy createEnemy(int x, int y, int enemyWidth, int enemyHeight) {
        Enemy enemy = new Enemy(x, y, enemyWidth, enemyHeight, 4);
        enemies.add(enemy);
        return enemy;
    }

    public void progressEnemies() {
        for(int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            enemy.setY(enemy.getY() + enemy.getSpeed());
        }
    }

    public static List<Enemy> getEnemies(){
        return enemies;
    }

    public void setChanceOfSpawn(double chanceOfSpawn) {
        this.chanceOfSpawn = chanceOfSpawn;
    }

    public boolean enemyWillSpawn(RandomWrapper randomWrapper) {
        double random = randomWrapper.getRandomDouble();
        return random <= chanceOfSpawn;
    }

    public class Enemy {

        private final int x;
        private int y;
        private final int width;
        private final int height;
        private final int speed;

        private Enemy(int x, int y, int width, int height, int speed){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.speed = speed;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getSpeed() { return speed; }

        public void setY(int y) { this.y = y; }
    }
}
