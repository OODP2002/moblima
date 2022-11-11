import java.util.HashMap;
import java.util.Scanner;

public interface MovieQuery {
    Scanner sc = new Scanner(System.in);

    default void listBy() {
        // CORRECTION: IT IS LIST ALL MOVIES SO THEY KNOW WHAT EXISTS
        MovieStore movStore = MovieStore.getInstance();

        System.out.println("List movies by: ");
        System.out.println("(1) Sales ");
        System.out.println("(2) Reviews ");
        System.out.println("Enter choice: ");

        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                // call movieStore listBySales method...?
                movStore.ListTop5(0);
                break;
            case 2:
                // call movieStore listByReviews method...?
                movStore.ListTop5(1);
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }

    default void listAllMovies() {
        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        for (Movie movie: movies.values()) {
            if (movie.getShowingStatus() != Status.ENDOFSHOWING)
                movie.printMovie();
        }
    }

    default void searchMovie() {
        MovieStore movStore = MovieStore.getInstance();

        int movieID = -1;
        System.out.println("-------Searching Movies-------");
        System.out.println("Enter MovieID (-1 to return): ");
        movieID = sc.nextInt();
        sc.nextLine();

        if (movieID == -1){
            return;
        }

        Movie movie = movStore.searchMovie(String.valueOf(movieID));
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            printMovieInfo(String.valueOf(movieID));
        }

    }

    default void searchMovie(String movieID) {
        MovieStore movStore = MovieStore.getInstance();
        Movie movie = movStore.searchMovie(movieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            printMovieInfo(movieID);
        }
    }

    private void printMovieInfo(String movieID) {
        Movie movie = MovieStore.getInstance().searchMovie(movieID);
        movie.printMovie();
    }
}
