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
    private final String FILE_SOURCE = "./src/showtimes.txt";
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

    public HashMap<String, ShowTime> getShowTimeHashMap() {
        return showTimeHashMap;
    }

    private void loadShowTimeHashMap() {
        for (String[] line: showTimeRawStore) {
            ShowTime showTime = new ShowTime(line[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            showTime.setStartTime(LocalDateTime.parse(line[2], formatter));
            showTime.setMovieID(Integer.parseInt(line[1]));
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
        sc.nextLine();

        System.out.println("Enter start time (DD-MM-YYYY HH:MM): ");
        String timeRaw = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeRaw, formatter);
            out.add(dateTime.format(formatter));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Default showtime of now is used");
            out.add(LocalDateTime.now().format(formatter));
        }

        System.out.println("Entry success!");
        System.out.println(String.join("|", out));       // for debugging, remove
        String[] outFormatted = new String[out.size()];
        outFormatted = out.toArray(outFormatted);
        showTimeRawStore.add(outFormatted);
    }

    private String generateShowTimeID(String cinemaID) {
        while (true) {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(3)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            String newKey = cinemaID.concat(generatedString);
            if (!showTimeHashMap.containsKey(newKey))
                return newKey;
        }
    }

    // WIP, incomplete
    public void updateShowtime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to update Show Time module");
        System.out.println("Enter showtime ID you wish to change: ");
        String showtimeID = sc.nextLine();

        if (!showtimeID.matches("[0-9][0-9][0-9][0-9][0-9a-zA-Z][0-9a-zA-Z][0-9a-zA-Z]")) {
            System.out.println("Invalid showtimeID, exiting update show time module");
            return;
        } else if (!showTimeHashMap.containsKey(showtimeID)) {
            System.out.println("Key not found, exiting update show time module");
            return;
        }

        ShowTime showTime = showTimeHashMap.get(showtimeID);
        boolean showtimeIDChanged = false;  // If true, re-create showtime object
        String newShowtimeID = showtimeID;
        boolean loop = true;

        while (loop) {
            System.out.println("Enter detail you wish to change\n" +
                    "(1) Cineplex\n" +
                    "(2) Cinema\n" +
                    "(3) Movie\n" +
                    "(4) Start Time\n" +
                    "(5) Quit\n");

            int input = sc.nextInt();

            switch (input){
                case 1 -> {
                    showtimeIDChanged = true;
                    System.out.println("Enter new Cineplex ID: ");
                    int cineplexID = sc.nextInt();
                    newShowtimeID = showtimeID.replace(showtimeID.substring(0,2), Integer.toString(cineplexID));
                    if (!CinemaStore.getInstance().isValidCinema(newShowtimeID)) {
                        System.out.println("Cineplex does not exist, create cinema in add cinema module first!");
                        break;
                    }
                    showTime.setShowtimeID(newShowtimeID);
                }

                case 2 -> {
                    showtimeIDChanged = true;
                    System.out.println("Enter new cinema ID: ");
                    int cinemaID = sc.nextInt();
                    newShowtimeID = showtimeID.replace(showtimeID.substring(2,4), Integer.toString(cinemaID));
                    if (!CinemaStore.getInstance().isValidCinema(newShowtimeID)) {
                        System.out.println("Cinema does not exist, create cinema in add cinema module first!");
                        break;
                    }
                    showTime.setShowtimeID(newShowtimeID);
                }

                case 3 -> {
                    System.out.println("Enter new movieID: ");
                    int movieID = sc.nextInt();
                    showTime.setMovieID(movieID);
                }

                case 4 -> {
                    System.out.println("Enter new start time (DD-MM-YYYY HH:MM): ");
                    String rawTimeIn = sc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(rawTimeIn, formatter);
                        showTime.setStartTime(dateTime);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Default showtime of now is used");
                        showTime.setStartTime(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
                    }
                }

                default -> {
                    System.out.println("Exiting update show time module...");
                    loop = false;
                }
            }
            if (showtimeIDChanged == true) {
                showTimeHashMap.remove(showtimeID);
                showTimeHashMap.put(newShowtimeID, showTime);
            } else {
                showTimeHashMap.replace(newShowtimeID, showTime);
            }
        }
    }
}
