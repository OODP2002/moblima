// Done by Mingyang

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class CineplexStore extends CineplexesReaderWriter{
    private HashMap<String, Cineplex> cineplexHashMap;
    private static CineplexStore single_instance = null;

    private CineplexStore() {
        this.cineplexHashMap = new HashMap<String, Cineplex>();
    }

    public static CineplexStore getInstance() {
        if (single_instance == null)
            single_instance = new CineplexStore();

        return single_instance;
    }

    public void readFile() throws IOException {
        BufferedReader reader = getReader();
        reader.readLine();  // Header row

        String line = reader.readLine();
        while (line != null) {
            String[] details = line.split("\\|");
            Cineplex cineplex = new Cineplex(details[1], details[0]);
            cineplexHashMap.put(details[0], cineplex);
            line = reader.readLine();
        }
    }

    public HashMap<String, Cineplex> getCineplexHashMap() {
        return cineplexHashMap;
    }
}
