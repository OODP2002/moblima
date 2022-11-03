// Done by Mingyang
import java.util.ArrayList;

public class Cinema {
    private int cinemaID;

    // Default cinema constructor: should never be used
    public Cinema() {
        this.cinemaID = -1;
    }

    public Cinema(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public int getCinemaID() {
        return cinemaID;
    }
}