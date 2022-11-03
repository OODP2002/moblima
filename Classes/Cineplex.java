// Done by Mingyang
import java.util.ArrayList;

public class Cineplex{
    private String name;
    private int cineplexID;
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    // Default cinema constructor: should never be called
    public Cineplex() {
        this.cineplexID = -1;
        this.name = "Cinema";
    }

    // Constructor
    public Cineplex(String name, int cineplexID) {
        this.cineplexID = cineplexID;
        this.name = name;
    }

    public int getCineplexID() {
        return cineplexID;
    }

    public String getCineplexName() {
        return name;
    }
}
