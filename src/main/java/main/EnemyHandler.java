package main;
import java.util.ArrayList;
import java.util.List;

public class EnemyHandler {
    public static List<Enemy> enemies = new ArrayList<>();

    public void createEnemy(int x, int y, int enemyWidth, int enemyHeight) {
        Enemy enemy = new Enemy(x, y, enemyWidth, enemyHeight);
        enemies.add(enemy);
    }

    public class Enemy {

        int x;
        int y;
        int width;
        int height;

        private Enemy(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
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
    }
}
