// Done by Mingyang

import java.io.Serializable;

public class Cinema implements Serializable {
    private int cinemaID;
    private CinemaClass cinemaClass;
//    private Layout layout = new Layout(10,10,2);
    private Layout layout;

    public Cinema(int cinemaID, CinemaClass cinemaClass, Layout layout) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
        this.layout = layout;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public Layout getLayout() {
        return layout;
    }

    public CinemaClassLevels getCinemaClass() {
        return cinemaClass.getCinemaClass();
    }
}