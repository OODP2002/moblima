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
    private int movieID;
    private ShowDate showDate;
    private ShowTimeLayout showTimeLayout;

    public ShowTime(String showtimeID) {
        this.showtimeID = showtimeID;
        this.showTimeLayout = new ShowTimeLayout(showtimeID.substring(0,4));
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

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getMovieID() {
        return movieID;
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