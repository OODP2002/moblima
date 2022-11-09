// Done by Mingyang

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ShowTimeStore {
    private HashMap<String, ShowTime> showTimeHashMap = new HashMap<>();  // key=SHOWTIME_ID
    private final String FILE_SOURCE = "Classes/src/showtimes.txt";
    private String HEADER;
    private static ShowTimeStore single_instance = null;
    private ArrayList<String[]> showTimeRawStore = new ArrayList<>();

    private ShowTimeStore() {
        readFile();
        loadShowTimeHashMap();
    }

    public static ShowTimeStore getInstance(){
        if (single_instance == null)
            single_instance = new ShowTimeStore();

        return single_instance;
    }

    private void loadShowTimeHashMap() {
        for (String[] line: showTimeRawStore) {
            ShowTime showTime = new ShowTime(line[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            showTime.setStartTime(LocalDateTime.parse(line[2], formatter));
            showTimeHashMap.put(line[0], showTime);
        }
    }

    public ShowTime getShowTime(String showTimeID) {
        return showTimeHashMap.get(showTimeID);
    }

    // Read from .txt into raw ArrayList<String[]>
    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.HEADER = reader.readLine();    // Header

            String line = reader.readLine();
            while (line != null) {
                showTimeRawStore.add(line.split("\\|"));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Write from ShowTimeStore into .txt
    // Call before terminating the program!!!
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(HEADER);
            for (String[] line : showTimeRawStore) {
                writer.write("\n" + String.join("|", line));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addShowTime() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> out = new ArrayList<>();
        System.out.println("Welcome to add ShowTime module");

        System.out.println("Enter Cineplex ID: ");
        String cineplexID = sc.next("[0-9][0-9]");
        sc.nextLine();

        System.out.println("Enter Cinema no.: ");
        String cinemaID = cineplexID.concat(sc.next("[0-9][0-9]"));
        out.add(generateShowTimeID(cinemaID));
        sc.nextLine();

        System.out.println("Enter movie ID: ");
        out.add(String.valueOf(sc.nextInt()));

        System.out.println("Enter start time (DD-MM-YYYY HH:MM): ");
        String timeRaw = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeRaw, formatter);
            out.add(String.valueOf(dateTime));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Default showtime of now is used");
            out.add(String.valueOf(LocalDateTime.now()));
        }

        System.out.println("Entry success!");
        System.out.println(String.join("|", out));       // for debugging, remove
        String[] outFormatted = new String[out.size()];
        outFormatted = out.toArray(outFormatted);
        showTimeRawStore.add(outFormatted);
    }

    private String generateShowTimeID(String cinemaID) {
        while (true) {
            RandomString gen = new RandomString(3, ThreadLocalRandom.current());
            String newKey = cinemaID.concat(gen.toString());
            if (!showTimeHashMap.containsKey(newKey))
                return newKey;
        }
    }
}
