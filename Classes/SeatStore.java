import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/* Done by Mingyang
*  Handle seats.txt
* */
public class SeatStore {
    private HashMap<String, Seat> seatHashMap = new HashMap<>();    // key=SHOWTIME_ID
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

    public HashMap<String, Seat> getSeatHashMap() {
        return seatHashMap;
    }

    private void loadSeatStoreHashMap() {
        for (String[] line: seatRawStore) {
            Seat seat = new Seat(line[1]);
            seat.setAvail(Boolean.parseBoolean(line[2]));
            seatHashMap.put(line[0], seat);
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

    // Write from SeatStore into .txt
    // Call before terminating the program!!!
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(HEADER);
            for (String[] line : seatRawStore) {
                writer.write("\n" + String.join("|", line));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
