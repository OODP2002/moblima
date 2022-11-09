import java.util.ArrayList;

public class demo {
    public static void main(String[] args) {
        MovieStore movieStore = MovieStore.getInstance();
        Movie temp = movieStore.getMovie(0);
        temp.printInfo();
    }
}
