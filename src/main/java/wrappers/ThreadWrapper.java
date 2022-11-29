package wrappers;

import main.DungeonCrawler;

import java.io.Serializable;

public class ThreadWrapper implements Serializable {

    public void createNewThreadWithDungeonCrawler(DungeonCrawler dungeonCrawler) {
        Thread thread = new Thread(dungeonCrawler);
        thread.start();
    }

    public void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void currentThreadInterrupt() {
        Thread.currentThread().interrupt();
    }

}
