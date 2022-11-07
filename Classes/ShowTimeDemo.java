import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowTimeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Instantiation: new Cinema obj
        Cinema cinema = new Cinema(3, new CinemaClass(CinemaClassLevels.GOLD));

        // Load showTimes from Cinema object to access it
        ArrayList<ShowTime> showTimes = cinema.getShowTimes();

        // Add new showtime: Get showtimeID first
        System.out.println("Enter showtimeID: ");
        String showTimeID = sc.nextLine();
        showTimes.add(new ShowTime(showTimeID));

        // Add new showtime: set startTime
        ShowTime myShowTime = showTimes.get(0);
        System.out.println("Enter start time (DD-MM-YYYY HH:MM): ");
        String timeRaw = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeRaw, formatter);
            myShowTime.setStartTime(dateTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Default showtime of now is used");
            myShowTime.setStartTime(LocalDateTime.now());
        }

        // Get startTime
        System.out.println(myShowTime.getStartTime().format(formatter));
    }
}
