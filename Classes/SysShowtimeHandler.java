import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Update showtimes and movies
/**
 * Handles the updating of showtimes and movies
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
interface SysShowtimeHandler {
    /**
     * Allows for user input to be accessed
     */
    Scanner sc = new Scanner(System.in);
    /**
     * Allows for showtimes to be updated
     */
    default void updateShowTime() {
        HashMap<String, ShowTime> showTimeHashMap = ShowTimeStore.getInstance().getShowTimeHashMap();

        System.out.println("Welcome to update Show Time module");
        System.out.println("Enter showtime ID you wish to change: ");
        String showtimeID = sc.nextLine();

        if (!showtimeID.matches("[0-9][0-9][0-9][0-9][0-9a-zA-Z][0-9a-zA-Z][0-9a-zA-Z]")) {
            System.out.println("Invalid showtimeID, exiting update show time module");
            return;
        } else if (!showTimeHashMap.containsKey(showtimeID)) {
            System.out.println("ShowtimeID not found, exiting update show time module");
            return;
        }

        ShowTime showTime = showTimeHashMap.get(showtimeID);
        boolean showtimeIDChanged = false;  // If true, re-create showtime object
        String newShowtimeID = showtimeID;
        boolean loop = true;

        while (loop) {
            System.out.println("""
                    Enter detail you wish to change
                    (1) Cineplex
                    (2) Cinema
                    (3) Movie
                    (4) Start Time
                    (5) Quit
                    """);

            int input = sc.nextInt();
            sc.nextLine();

            switch (input){
                case 1 -> {
                    showtimeIDChanged = true;
                    System.out.println("Enter new Cineplex ID: ");
                    int cineplexID = sc.nextInt();
                    sc.nextLine();
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
                    sc.nextLine();
                    newShowtimeID = showtimeID.replace(showtimeID.substring(2,4), Integer.toString(cinemaID));
                    if (!CinemaStore.getInstance().isValidCinema(newShowtimeID)) {
                        System.out.println("Cinema does not exist, create cinema in add cinema module first!");
                        break;
                    }
                    showTime.setShowtimeID(newShowtimeID);
                }

                case 3 -> {
                    System.out.println("Enter new movieID: ");
                    String movieID = sc.nextLine();
                    showTime.setMovieID(movieID);
                }

                case 4 -> {
                    System.out.println("Enter new start time (DD-MM-YYYY HH:MM): ");
                    setShowtime(showTime);
                }

                default -> {
                    System.out.println("Exiting update show time module...");
                    loop = false;
                }
            }
            if (showtimeIDChanged) {
                showTimeHashMap.remove(showtimeID);
                showTimeHashMap.put(newShowtimeID, showTime);
            } else {
                showTimeHashMap.replace(newShowtimeID, showTime);
            }
        }
    }

    /**
     * Allows for showtimes to be added
     */
    default void addShowTime() {
        HashMap<String, ShowTime> showTimeHashMap = ShowTimeStore.getInstance().getShowTimeHashMap();

        System.out.println("Welcome to add ShowTime module");

        System.out.println("Enter Cineplex ID: ");
        String cineplexID = sc.next("[0-9][0-9]");
        sc.nextLine();

        System.out.println("Enter Cinema no.: ");
        String cinemaID = cineplexID.concat(sc.next("[0-9][0-9]"));
        sc.nextLine();
        String showtimeID = generateShowTimeID(cinemaID);
        ShowTime showTime = new ShowTime(showtimeID);

        System.out.println("Enter movie ID: ");
        showTime.setMovieID(sc.nextLine());

        System.out.println("Enter start time (DD-MM-YYYY HH:MM): ");
        setShowtime(showTime);

        System.out.println("Entry success!");
        showTimeHashMap.put(showtimeID, showTime);
    }

    /**
     * Allows for showtimes to be removed
     */
    default void removeShowTime() {
        HashMap<String, ShowTime> showTimeHashMap = ShowTimeStore.getInstance().getShowTimeHashMap();
        System.out.println("Enter showtime ID to remove: ");
        String showtimeID = sc.nextLine();

        if (showTimeHashMap.remove(showtimeID) == null)
            System.out.println("Error removing showtime, showtime not removed");
        else
            System.out.println("Showtime successfully removed!");
    }



    /**
     * Sets a showtime for a particular Showtime object 
     * @param showTime Showtime parameter provided to set a showtime
     */
    private void setShowtime(ShowTime showTime) {
        String timeRaw = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeRaw, formatter);
            showTime.setStartTime(dateTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Default showtime of now is used");
            showTime.setStartTime(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        }
    }

    /**
     * Generates a showtime ID based on Cinema ID provided
     * @param cinemaID the Cinema ID provided to generate a showtimeID
     * @return showtime ID in String format
     */
    private String generateShowTimeID(String cinemaID) {
        HashMap<String, ShowTime> showTimeHashMap = ShowTimeStore.getInstance().getShowTimeHashMap();

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
}
