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
        for(int i = 0; i < lasers.size(); i++){
            Laser laser = lasers.get(i);
            laser.setY(laser.getY() - (1));
            if(laser.getY() < 5){
                lasers.remove(lasers.get(i));
            }
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

        public void setY(int y) {
            this.y = y;
        }
    }
}
