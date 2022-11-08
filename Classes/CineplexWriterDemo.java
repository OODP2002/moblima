import java.io.IOException;

public class CineplexWriterDemo {
    public static void main(String[] args) {
        CinemaStore cs = CinemaStore.getInstance();
        System.out.println(cs.getCinema("0001").getCinemaClass());

        // Add to raw ArrayList
        CineplexesReaderWriter.getInstance().addCineplexData();

        // Write to .txt file before program termination
        CineplexesReaderWriter.getInstance().writeFile();
    }
}
