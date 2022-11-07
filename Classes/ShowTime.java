import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShowTime {
    private String showtimeID;
    private LocalDateTime startTime;
//    private Movie movie;
//    private ShowDate showDate;
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
}
