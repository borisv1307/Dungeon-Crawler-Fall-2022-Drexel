package wrappers;

import java.util.TimerTask;

public class TimerTaskWrapper extends TimerTask {

    long timeToExecute;


    @Override
    public void run() {
        runTask();
    }

    public void runTask() {
        try {
            System.out.println("Starting task...");
            Thread.sleep(timeToExecute);
            System.out.println("After execution!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTimeToExecute(long timeToExecute) {
        this.timeToExecute = timeToExecute;
    }
}
