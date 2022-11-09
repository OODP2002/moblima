// Mingyang

import java.util.Collection;

public class CineplexWriterDemo {
    public static void main(String[] args) {
        CinemaStore cs = CinemaStore.getInstance();
//        System.out.println(cs.getCinema("0001").getCinemaClass());

        // Add to raw ArrayList
//        CineplexesReaderWriter.getInstance().addCineplexData();

        // Write to .txt file before program termination
        CineplexesReaderWriter.getInstance().writeFile();

        Cineplex cineplex1 = CineplexStore.getInstance().getCineplex("00");
        Collection<Cinema> cinemas = cineplex1.getCinemaHashMap().values();
        for (Cinema c: cinemas) {
            System.out.println(c.getCinemaID());
            System.out.println(c.getCinemaClass());
        }
    }
}
