// Done by Mingyang

import java.util.ArrayList;

public class Cinema {
    private String cinemaID;
    private CinemaClass cinemaClass;
    private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();

    public Cinema(String cinemaID, CinemaClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public CinemaClassLevels getCinemaClass() {
        return cinemaClass.getCinemaClass();
    }

    public ArrayList<ShowTime> getShowTimes() {
        return showTimes;
    }
}