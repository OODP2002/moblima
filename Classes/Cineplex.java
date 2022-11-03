// Done by Mingyang
import java.util.ArrayList;

public class Cineplex{
    private String name;
    private int cineplexID;
    public ArrayList<Cinema> cinemas = new ArrayList<>();

    // Default cineplex constructor: should never be called
    public Cineplex() {
        this.cineplexID = -1;
        this.name = "Cinema";
    }

    // Constructor: 3 cinemas are added by default
    public Cineplex(String name, int cineplexID) {
        this.cineplexID = cineplexID;
        this.name = name;
        for (int i = 0; i < 3; i++) {
            addCinema(cinemas.size() + 1);
        }
    }

    public int getCineplexID() {
        return cineplexID;
    }

    public String getCineplexName() {
        return name;
    }

    public void addCinema(int cinemaID) {
        cinemas.add(new Cinema(cinemaID, STANDARD));
    }
}
