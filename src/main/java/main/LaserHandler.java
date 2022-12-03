package main;

import java.util.ArrayList;
import java.util.List;

public class LaserHandler {

    private List<Laser> lasers;

    public LaserHandler(){
        lasers = new ArrayList<>();
    }

    public Laser laserFactory(int x, int y) {
        Laser laser = new Laser(x, y);
        lasers.add(laser);
        return laser;
    }

    public void progressLasers() {
        for(int i = 0; i < lasers.size(); i++){
            Laser laser = lasers.get(i);
            laser.setY(laser.getY() - (1));
            if(laser.getY() < 5){
                destroy(lasers.get(i));
            }
        }
    }
    public List<Laser> getLasers(){
        return this.lasers;
    }

    public void destroy(Laser laser) {
        lasers.remove(laser);
    }

    public class Laser {
        int x;
        int y;

        private Laser(int x, int y){
            this.x = x;
            this.y = y;
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
