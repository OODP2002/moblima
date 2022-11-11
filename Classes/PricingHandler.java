import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PricingHandler {
    private PricingStore ps = PricingStore.getInstance();
    private ShowTimeStore ss = ShowTimeStore.getInstance();
    private SpecialOccasionStore sos = SpecialOccasionStore.getInstance();
    private MovieStore ms = MovieStore.getInstance();
    private CinemaStore cs = CinemaStore.getInstance();
    private static PricingHandler instance = new PricingHandler();

    public static PricingHandler getInstance(){
        return instance; 
    }

    public Float queryPrice(String TransactionID, AgeGroup ageGroup){
        //Extract IDs
        String showTimeID = TransactionID.substring(0, 10);
        String cinemaID = TransactionID.substring(0,4);

        //Information from showtime store and special ocassion store
        ShowTime showTime = ss.getShowTime(showTimeID);
        LocalDateTime startDateTime = showTime.getStartTime();
        LocalTime time = startDateTime.toLocalTime();
        LocalDate date = startDateTime.toLocalDate();
        Integer dayOfWeek = date.getDayOfWeek().getValue();
        boolean isPH = sos.isSpecialOccasion(date);

        
        //Information from movie store
        Integer movieID = showTime.getMovieID();
        Movie movie = ms.getMovie(String.valueOf(movieID));
        Hype hype = movie.getMovieHype();
        View view = movie.getViewingMode();

        
        //CinemaClass
        Cinema cinema = cs.getCinema(cinemaID);
        CinemaClass cinemaClass = cinema.getCinemaClass();

        
        return ps.queryPrice(ageGroup, hype, cinemaClass, isPH,  dayOfWeek, time, view);
    }
    
}
