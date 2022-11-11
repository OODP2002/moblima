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

    default void printAllMovies() {
        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        for (Movie movie: movies.values()) {
            System.out.println("=======================");
            System.out.println("Movie name: " + movie.getMovieName());
            System.out.println("Movie ID: "+ movie.getMovieID());
            System.out.printf("Movie details: %s, %s\n", movie.getMovieHype(), movie.getViewingMode());
            System.out.println("Movie is rated: " + movie.getAgeRating());
            System.out.println("Sypnosis: " + movie.getSynopsis());
            System.out.println("=======================");
        }
    }
}
