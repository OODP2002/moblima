import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
* @author mingyang
* Purpose of ShowTime class: ShowTime obj that has-a ShowTime layout
* ShowTime has no Seats
* */
public class ShowTime {
    private String showtimeID;
    private LocalDateTime startTime;
    private String movieID;
    private Movie movie;
    private Cinema cinema;
    private ShowTimeLayout showTimeLayout;

    public ShowTime(String showtimeID) {
        this.showtimeID = showtimeID;
        this.showTimeLayout = new ShowTimeLayout(showtimeID);
        HashMap <String, Cinema> cinemaHashMap = CineplexStore.getInstance().getCineplex(showtimeID.substring(0,2)).getCinemaHashMap();
        this.cinema = cinemaHashMap.get(showtimeID.substring(0,4));
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
        this.movie = MovieStore.getInstance().searchMovie(movieID);
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
        System.out.println("Movie ID: " + this.movie.getMovieID());
        System.out.println("Movie Name: " + this.movie.getMovieName());
        System.out.println("Date: " + getStartTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        System.out.println("Starting Time: " + getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}