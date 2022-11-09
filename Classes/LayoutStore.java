/*
    Done by Mingyang
    LayoutStore will read and write Layout from Cineplexes.txt and store them in a HashMap
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LayoutStore{
    private HashMap<String, Layout> layoutHashMap;  // Key=cinemaID
    private static LayoutStore single_instance = null;

    private LayoutStore() {
        layoutHashMap = new HashMap<>();
        loadLayoutHashMap();
    }

    public static LayoutStore getInstance() {
        if (single_instance == null)
            single_instance = new LayoutStore();

        return single_instance;
    }

    private void loadLayoutHashMap() {
        ArrayList<String[]> temp = CineplexesReaderWriter.getInstance().getCineplexRawStore();
        for (String[] line: temp) {
            String cinemaID = line[0].concat(line[2]);
            int row = Integer.parseInt(line[4]);
            int column = Integer.parseInt(line[5]);
            int aisle = Integer.parseInt(line[6]);
            int mainStairway = Integer.parseInt(line[7]);

            Layout layout = new Layout(row, column, aisle, mainStairway);
            layoutHashMap.put(cinemaID, layout);
        }
    }

    public Layout getLayout(String cinemaID) {
        return layoutHashMap.get(cinemaID);
    }
}
