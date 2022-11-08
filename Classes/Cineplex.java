// Done by Mingyang
import java.util.ArrayList;

public class Cineplex{
    private String name;
    private String cineplexID;
    public ArrayList<Cinema> cinemas = new ArrayList<>();

    // Constructor: 3 cinemas are added by default
    public Cineplex(String name, String cineplexID) {
        this.cineplexID = cineplexID;
        this.name = name;
        for (int i = 0; i < 3; i++) {
            addCinema(cinemas.size() + 1);
        }
    }

    public String getCineplexID() {
        return cineplexID;
    }

    public String getCineplexName() {
        return name;
    }

    public void addCinema(int cinemaID) {
        cinemas.add(new Cinema(cinemaID, new CinemaClass(CinemaClassLevels.STANDARD)));
    }
}
