// Done by Mingyang

public class Cinema {
    private int cinemaID;
    private CinemaClass cinemaClass = new CinemaClass();

    public Cinema(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public CinemaClassLevels getCinemaClass() {
        return cinemaClass.getCinemaClass();
    }
}