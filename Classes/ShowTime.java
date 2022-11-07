import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShowTime {
    private String showtimeID;
    private LocalDateTime startTime;
//    private Movie movie;
    private ShowDate showDate;
//    private ArrayList<Seat> seats;

    public ShowTime(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void printShowTime() {
        String cinemaID = showtimeID.substring(0,1);
//        System.out.println("Movie showing is: " + movie.getMovieName());
        System.out.println("Date of showing: " + startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("Movie starts at " + startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " at Cinema " + cinemaID);
    }
}
