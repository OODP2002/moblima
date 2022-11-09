// Done by Mingyang
import java.io.IOException;
import java.util.HashMap;

public class Cineplex{
    private String name;
    private String cineplexID;
    private HashMap<String, Cinema> cinemaHashMap;

    // Constructor: 3 cinemas are added by default
    public Cineplex(String name, String cineplexID) {
        this.cineplexID = cineplexID;
        this.name = name;
        addCinema();
    }

    public String getCineplexID() {
        return cineplexID;
    }

    public String getCineplexName() {
        return name;
    }

    public void addCinema() {
        CinemaStore cinemaStore = CinemaStore.getInstance();
        this.cinemaHashMap = cinemaStore.getCinemaHashMap();
    }

    public HashMap<String, Cinema> getCinemaHashMap() {
        return cinemaHashMap;
    }
}
