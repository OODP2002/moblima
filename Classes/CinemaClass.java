// Done by Mingyang
public class CinemaClass {
    private CinemaClassLevels cinemaClass;

    public CinemaClass() {
        this.cinemaClass = CinemaClassLevels.STANDARD;
    }

    public CinemaClass(CinemaClassLevels cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public CinemaClassLevels getCinemaClass() {
        return cinemaClass;
    }
}