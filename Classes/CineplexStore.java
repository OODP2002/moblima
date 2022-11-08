import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class CineplexStore extends CineplexesReaderWriter{
    private HashMap<String, Cineplex> cineplexMap;

    public CineplexStore() {
        this.cineplexMap = new HashMap<String, Cineplex>();
    }
    public void readFile() throws IOException {
        BufferedReader reader = getReader();
        reader.readLine();  // Header row

        String line = reader.readLine();
        while (line != null) {
            String[] details = line.split("\\|");
            Cineplex cineplex = new Cineplex(details[1], details[0]);
            cineplexMap.put(details[0], cineplex);
            line = reader.readLine();
        }
    }

    public HashMap<String, Cineplex> getCineplexMap() {
        return cineplexMap;
    }
}
