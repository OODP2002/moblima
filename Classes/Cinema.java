// Done by Mingyang

public class Cinema {
    private int cinemaID;
    private CinemaClass cinemaClass;

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