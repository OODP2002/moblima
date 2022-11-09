import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ShowTime {
    private String showtimeID;
    private LocalDateTime startTime;
    private int movieID;
    private ShowDate showDate;
    private Layout showTimeLayout;
    private HashMap<String, Seat> seats;

    public ShowTime(String showtimeID) {
        this.showtimeID = showtimeID;
        this.showTimeLayout = LayoutStore.getInstance().getLayout(showtimeID.substring(0,4));
        this.seats = showTimeLayout.createSeats();
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

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getMovieID() {
        return movieID;
    }

    // Return Seat object
    public Seat getSeat(String seatID) {
        return seats.get(seatID);
    }

    public void printShowTime() {
        String cineplexID = showtimeID.substring(0,2);
        String cinemaID = showtimeID.substring(2,4);
//        System.out.println("Movie showing is: " + movie.getMovieName());
        System.out.println("Date of showing: " + startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("Movie starts at " + startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " at Cinema " + cinemaID);
        System.out.println("Location: " + CineplexStore.getInstance().getCineplex(cineplexID).getCineplexName());
    }
}