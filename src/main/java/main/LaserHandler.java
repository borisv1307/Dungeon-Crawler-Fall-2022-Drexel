package main;

import java.util.ArrayList;
import java.util.List;

public class LaserHandler {

    public List<Laser> lasers;

    public LaserHandler(){
        lasers = new ArrayList<>();
    }

    public Laser laserFactory(int x, int y) {
        Laser laser = new Laser(x, y, 10, 10);
        lasers.add(laser);
        return laser;
    }

    public void progressLasers() {
        for(Laser laser : lasers){
            laser.setY(laser.getY() - (1));
        }
    }

    public class Laser {
        int x;
        int y;
        int width;
        int height;

        private Laser(int x, int y, int width, int height){
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
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
