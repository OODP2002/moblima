// Done by Mingyang

import java.util.ArrayList;

public class Cinema {
    private String cinemaID;
    private CinemaClass cinemaClass;
//    private ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
    private Layout layout;

    public Cinema(String cinemaID, CinemaClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
        LayoutStore layoutStore = LayoutStore.getInstance();
        this.layout= layoutStore.getLayout(cinemaID);
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public Layout getLayout() {
        return layout;
    }

    //    public ArrayList<ShowTime> getShowTimes() {
//        return showTimes;
//    }
}