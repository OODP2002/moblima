// Done by Mingyang

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ShowTimeStore {
    private HashMap<String, ShowTime> showTimeHashMap = new HashMap<>();  // key=SHOWTIME_ID
    private final String FILE_SOURCE = "Classes/src/showtimes.txt";
    private static ShowTimeStore single_instance = null;
    private Scanner sc = new Scanner(System.in);
    private TxtReaderWriter showtimeReaderWriter = new TxtReaderWriter(FILE_SOURCE);

    private ShowTimeStore() {
        loadShowTimeHashMap(showtimeReaderWriter.getRawStringFromFile());
    }

    public static ShowTimeStore getInstance(){
        if (single_instance == null)
            single_instance = new ShowTimeStore();

        return single_instance;
    }

    public HashMap<String, ShowTime> getShowTimeHashMap() {
        return showTimeHashMap;
    }

    // Returns null if showtimeID does not exist
    public ShowTime getShowTime(String showTimeID) {
        return showTimeHashMap.get(showTimeID);
    }


    private void loadShowTimeHashMap(ArrayList<String[]> showtimeRawStore) {
        for (String[] line: showtimeRawStore) {
            ShowTime showTime = new ShowTime(line[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            showTime.setStartTime(LocalDateTime.parse(line[2], formatter));
            showTime.setMovieID(line[1]);
            showTimeHashMap.put(line[0], showTime);
        }
    }

    // parseHashMap to ArrayList<String[]>
    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        Set<String> keys = showTimeHashMap.keySet();

        // Iterate over each ShowTime item
        for (String key: keys) {
            ShowTime showTime = showTimeHashMap.get(key);
            ArrayList<String> line = new ArrayList<>();

            line.add(showTime.getShowtimeID());
            line.add(String.valueOf(showTime.getMovieID()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            line.add(showTime.getStartTime().format(formatter));

            String[] out = new String[line.size()];
            arrayListOut.add(line.toArray(out));
        }
        return arrayListOut;
    }

    // Destructor
    public void closeShowTimeStore() {
        showtimeReaderWriter.setRawStringFromFile(parseHashMap());
    }
}
