/*
    Done by Mingyang
    LayoutStore will read and write Layout from Cineplexes.txt and store them in a HashMap
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class LayoutStore extends CineplexesReaderWriter{
    private HashMap<String, Layout> layoutHashMap;
    private static LayoutStore single_instance = null;

    private LayoutStore() {
        layoutHashMap = new HashMap<>();
        try {
            readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static LayoutStore getInstance() {
        if (single_instance == null)
            single_instance = new LayoutStore();

        return single_instance;
    }

    private void readFile() throws IOException {
        BufferedReader reader = getReader();
        reader.readLine();      // Header row

        String line = reader.readLine();
        while (line != null) {
            String[] details = line.split("\\|");

            String cinemaID = details[0].concat(details[2]);
            int row = Integer.parseInt(details[4]);
            int column = Integer.parseInt(details[5]);
            int aisle = Integer.parseInt(details[6]);
            int mainStairway = Integer.parseInt(details[7]);

            layoutHashMap.put(cinemaID, new Layout(row, column, aisle, mainStairway));
        }
    }

    public Layout getLayout(String cinemaID) {
        return layoutHashMap.get(cinemaID);
    }
}
