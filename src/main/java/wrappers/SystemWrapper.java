package wrappers;

import java.io.Serializable;

public class SystemWrapper implements Serializable {

    public long nanoTime() {
        return System.nanoTime();
    }
}
