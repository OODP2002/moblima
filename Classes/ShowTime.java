import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* ShowTime class created by Mingyang
* Purpose of ShowTime class: ShowTime obj that has-a ShowTime layout
* ShowTime has no Seats
* */
public class ShowTime {
    private String showtimeID;
    private LocalDateTime startTime;
    private String movieID;
    private Cinema cinema;
    private ShowTimeLayout showTimeLayout;

    public ShowTime(String showtimeID) {
        this.showtimeID = showtimeID;
        this.showTimeLayout = new ShowTimeLayout(showtimeID.substring(0,4));
        this.cinema = CinemaStore.getInstance().getCinema(showtimeID.substring(2,4));
    }

    public ShowTimeLayout getShowTimeLayout() {
        return showTimeLayout;
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

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getMovieID() {
        return movieID;
    }

    public CinemaClass getCinemaClass() {
        return cinema.getCinemaClass();
    }
}