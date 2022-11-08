import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class CinemaStore extends CineplexesReaderWriter{
    private HashMap<String, Cinema> cinemaHashMap;
    private static CinemaStore single_instance = null;

    private CinemaStore() {
        this.cinemaHashMap = new HashMap<>();
    }

    public static CinemaStore getInstance() {
        if (single_instance == null)
            single_instance = new CinemaStore();

        return single_instance;
    }

    public void readFile() throws IOException {
        BufferedReader reader = getReader();
        reader.readLine();  // Header row

        String line = reader.readLine();
        while (line != null) {
            String[] details = line.split("\\|");
            Cinema cinema = new Cinema(details[0], new CinemaClass(CinemaClassLevels.GOLD));
            cinemaHashMap.put(details[0], cinema);
            line = reader.readLine();
        }
    }

    public HashMap<String, Cinema> getCinemaHashMap() {
        return cinemaHashMap;
    }
}
