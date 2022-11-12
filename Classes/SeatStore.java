import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/* Done by Mingyang
*  Handle seats.txt
* */
public class SeatStore {
    private Map<String, ArrayList<String>> seatHashMap = new HashMap<>();    // key=SHOWTIME_ID
    private static SeatStore single_instance = null;
    private ArrayList<String[]> seatRawStore = new ArrayList<>();
    private final String FILE_SOURCE = "Classes/src/seats.txt";
    private String HEADER;

    private SeatStore() {
        readFile();
        loadSeatStoreHashMap();
    }

    public static SeatStore getInstance() {
        if (single_instance == null)
            single_instance = new SeatStore();

        return single_instance;
    }

    private void loadSeatStoreHashMap() {
        for (String[] line: seatRawStore) {
            ArrayList<String> occupied = new ArrayList<>();
            occupied.addAll(Arrays.asList(line[1].split("~")));
            seatHashMap.put(line[0], occupied);
        }
    }

    // Read from .txt into raw ArrayList<String[]>
    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.HEADER = reader.readLine();    // Header

            String line = reader.readLine();
            while (line != null) {
                seatRawStore.add(line.split("\\|"));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getSeatsForShowTime(String showTimeID){
        return seatHashMap.get(showTimeID);
    }

    public void occupySeat(String showTimeID, String seatID){
        if(seatHashMap.get(showTimeID) == null){
            ArrayList<String> occupied = new ArrayList<>(); 
            occupied.add(seatID);
            seatHashMap.put(showTimeID, occupied);
        } else {
            seatHashMap.get(showTimeID).add(seatID);
        }
    }

    // Write from SeatStore into .txt
    // Call before terminating the program!!!
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(HEADER);
            for (Map.Entry<String, ArrayList<String>> entry : seatHashMap.entrySet()) {
                writer.write(entry.getKey() + "|" + String.join("~", entry.getValue()) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
