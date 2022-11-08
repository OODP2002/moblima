import java.io.IOException;

public class CineplexWriterDemo {
    public static void main(String[] args) {
        CineplexesReaderWriter crw = new CineplexesReaderWriter();
        try {
            crw.writeFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
