import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CinemaStore{
    private HashMap<String, Cinema> cinemaHashMap;
    private static CinemaStore single_instance = null;

    private CinemaStore() {
        this.cinemaHashMap = new HashMap<>();
        loadCinemaHashMap();
    }

    // Singleton
    public static CinemaStore getInstance() {
        if (single_instance == null)
            single_instance = new CinemaStore();

        return single_instance;
    }

    private void loadCinemaHashMap() {
        ArrayList<String[]> temp = CineplexesReaderWriter.getInstance().getCineplexRawStore();
        for (String[] line: temp) {
            String cinemaID = line[0].concat(line[2]);
            Cinema cinema = new Cinema(cinemaID, CinemaClass.valueOf(line[3]));
            cinemaHashMap.put(cinemaID, cinema);
        }
    }


    public HashMap<String, Cinema> getCinemaHashMap() {
        return cinemaHashMap;
    }


    public Cinema getCinema(String cinemaID) {
        return cinemaHashMap.get(cinemaID);
    }
}
