package wrappers;


public class SystemWrapper {

    public long nanoTime() {
        return System.nanoTime();
    }

    public void printLn(Object object) {
        System.out.println(object);
    }

}
