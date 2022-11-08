// Done by Mingyang

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class CineplexesReaderWriter {
    private final String FILESOURCE = "Classes/src/cineplexes.txt";
    private BufferedReader reader;

    public CineplexesReaderWriter() {
        try {
            this.reader = new BufferedReader(new FileReader(FILESOURCE));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error: cineplexes.txt not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void readFile() throws IOException {

    }

    //    public BufferedWriter writeFile() {
//
//    }
}
