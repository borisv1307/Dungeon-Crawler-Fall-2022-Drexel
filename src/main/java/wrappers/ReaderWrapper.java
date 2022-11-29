package wrappers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;

public class ReaderWrapper implements Serializable {

    FileReader createFileReader(String fileName) throws FileNotFoundException {
        FileReader fileReader = null;
        fileReader = new FileReader(fileName);
        return fileReader;
    }

    public BufferedReader createBufferedReader(String fileName) throws FileNotFoundException {
        FileReader fileReader = createFileReader(fileName);
        return new BufferedReader(fileReader);
    }
}