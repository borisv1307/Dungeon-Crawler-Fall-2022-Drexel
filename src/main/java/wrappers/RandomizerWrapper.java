package wrappers;

public class RandomizerWrapper {
    SystemWrapper systemWrapper;


    public RandomizerWrapper(SystemWrapper systemWrapper) {
        this.systemWrapper = systemWrapper;
    }

    public int getNonRandomInt(int limit) {
        long nanoTime = systemWrapper.milliTime();
        int digit = (int) Math.abs(nanoTime % 100);

        return digit % -limit;
    }


}
