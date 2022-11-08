import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class CinemaStore extends CineplexesReaderWriter{
    private HashMap<String, Cinema> cinemaHashMap;
    private static CinemaStore single_instance = null;

    private CinemaStore() {
        this.cinemaHashMap = new HashMap<>();
        try {
            readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static CinemaStore getInstance() {
        if (single_instance == null)
            single_instance = new CinemaStore();

        return single_instance;
    }

    private void readFile() throws IOException {
        BufferedReader reader = getReader();
        reader.readLine();  // Header row

        String line = reader.readLine();
        while (line != null) {
            String[] details = line.split("\\|");
            String cinemaID = details[0].concat(details[2]);
            CinemaClass cinemaClass;
            try {
                cinemaClass = CinemaClass.valueOf(details[3]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Default class of STANDARD is used");
                cinemaClass = CinemaClass.STANDARD;
            }
            Cinema cinema = new Cinema(cinemaID, cinemaClass);
            cinemaHashMap.put(cinemaID, cinema);
            line = reader.readLine();
        }
        reader.close();
    }

    public HashMap<String, Cinema> getCinemaHashMap() {
        return cinemaHashMap;
    }
}
