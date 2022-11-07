// Done by Mingyang

import java.util.ArrayList;

public class Cinema {
    private int cinemaID;
    private CinemaClass cinemaClass;
    private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();

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

    public ArrayList<ShowTime> getShowTimes() {
        return showTimes;
    }
}