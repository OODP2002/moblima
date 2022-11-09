// Done by Mingyang

import java.util.ArrayList;
import java.util.HashMap;

public class CineplexStore {
    private HashMap<String, Cineplex> cineplexHashMap;
    private static CineplexStore single_instance = null;

    private CineplexStore() {
        this.cineplexHashMap = new HashMap<String, Cineplex>();
        loadCineplexHashMap();
    }

    public static CineplexStore getInstance() {
        if (single_instance == null)
            single_instance = new CineplexStore();

        return single_instance;
    }

    private void loadCineplexHashMap() {
        ArrayList<String[]> temp = CineplexesReaderWriter.getInstance().getCineplexRawStore();
        for (String[] line: temp) {
            Cineplex cineplex = new Cineplex(line[1], line[0]);
            cineplexHashMap.put(line[0], cineplex);
        }
    }

    // This function is for Vendor class to get a Hashmap of cineplexes
    public HashMap<String, Cineplex> getCineplexHashMap() {
        return cineplexHashMap;
    }

    // For query of Cineplex by cineplexID
    public Cineplex getCineplex(String cineplexID) {
        return cineplexHashMap.get(cineplexID);
    }
}
