import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CinemaStore{
    private HashMap<String, Cinema> cinemaHashMap = new HashMap<>();  // Key=cinemaID
    private static CinemaStore single_instance = null;

    private CinemaStore() {
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


    // Return the cinemaHashMap for the corresponding CineplexID
    public HashMap<String, Cinema> getCinemaHashMap(String cineplexID) {
        // Find intersection between keys and cineplexID
        Set<String> keys = cinemaHashMap.keySet();
        keys.removeIf(key -> !key.substring(0, 2).equals(cineplexID));

        // Create new HashMap with only relevant keys
        HashMap<String, Cinema> tempHashMap = new HashMap<>();
        for (String key: keys) {
            Cinema cinema = cinemaHashMap.get(key);
            tempHashMap.put(key, cinema);
        }
        return tempHashMap;
    }


    public Cinema getCinema(String cinemaID) {
        return cinemaHashMap.get(cinemaID);
    }

    // Returns true if cinemaID is valid (existing cinema)
    public boolean isValidCinema(String cinemaID) {
        return cinemaHashMap.containsKey(cinemaID);
    }
}
