import java.io.Serializable;

// Done by Mingyang
public class CinemaClass implements Serializable {
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