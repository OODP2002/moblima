// Done by Mingyang

import java.io.Serializable;

public class Cinema implements Serializable {
    private int cinemaID;
    private CinemaClass cinemaClass;
    private Layout layout = new Layout(10,10,2);

    public Cinema(int cinemaID, CinemaClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public CinemaClassLevels getCinemaClass() {
        return cinemaClass.getCinemaClass();
    }
}