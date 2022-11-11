import java.util.HashMap;
import java.util.Scanner;

public interface SysMovieHandler {
    Scanner sc = new Scanner(System.in);

    default void createNewMovie() {

    }

    default void updateMovie() {

    }

    default void removeMovie() {
        MovieStore movieStore = MovieStore.getInstance();
        System.out.print("Enter movie ID you wish to remove");
        String movieID = sc.nextLine();
        if (movieStore.removeMovie(movieID) == null)
            System.out.println("Error: movie not found");
        else
            System.out.println(movieID + " removed successfully!");
    }

    // Admin implementation of printAll movies shows ALL details
    default void printAllMovies() {
        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        for (Movie movie: movies.values()) {
            movie.printMovie();
        }
    }
}
