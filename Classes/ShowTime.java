import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* @author mingyang
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
        this.showTimeLayout = new ShowTimeLayout(showtimeID);
        this.cinema = CineplexStore.getInstance().getCineplex(showtimeID.substring(0,2)).getCinemaHashMap().get(showtimeID.substring(0,4));
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
    public void printShowTime() {
        System.out.println("Cineplex: "+ CineplexStore.getInstance().getCineplex(showtimeID.substring(0,2)).getCineplexName());
        System.out.println("Cinema: "+ showtimeID.substring(2, 4));
        System.out.println("Cinema Class: " + this.cinema.getCinemaClass());
        //System.out.println("Cinema Class: " + CineplexStore.getInstance().getCineplex(showtimeID.substring(0,2)).getCinemaHashMap().get(showtimeID.substring(0,4)).getCinemaClass());
        System.out.println("Date: " + getStartTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        System.out.println("Starting Time: " + getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}