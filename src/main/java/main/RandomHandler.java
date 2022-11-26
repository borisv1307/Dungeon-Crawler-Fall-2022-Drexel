package main;

import wrappers.RandomWrapper;

public class RandomHandler {

    private final RandomWrapper randomWrapper;

    public RandomHandler(RandomWrapper randomWrapper){
        this.randomWrapper = randomWrapper;
    }
    
    public int getRandomIntInRange(int floor, int ceiling){
        double rand = randomWrapper.getRandomDouble();
        return (int) (rand * (ceiling - floor)) + floor;
    }
}
