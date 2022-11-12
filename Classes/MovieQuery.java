import java.util.HashMap;
import java.util.Scanner;

public interface MovieQuery extends SysSettings{
    Scanner sc = new Scanner(System.in);

    default void listBy() {
        // CORRECTION: IT IS LIST ALL MOVIES SO THEY KNOW WHAT EXISTS
        MovieStore movStore = MovieStore.getInstance();
        String listing_option = getListBy();

        switch (listing_option) {
            case "NIL" -> listByOptions();
            case "SALES" -> movStore.ListTop5(0);
            case "AVG_RTG" -> movStore.ListTop5(1);
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

        System.out.println("-------Searching Movies-------");
        System.out.println("Enter MovieID (-1 to return): ");
        String movieID = sc.nextLine();

        if (movieID.equals("-1")){
            return;
        }

        Movie movie = movStore.searchMovie(movieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            printMovieInfo(movieID);
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

    private void listByOptions() {
        MovieStore movStore = MovieStore.getInstance();

        System.out.println("List movies by: ");
        System.out.println("(1) Sales ");
        System.out.println("(2) Average Rating ");
        System.out.println("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice) {
            case 1:
                movStore.ListTop5(0);
                break;
            case 2:
                movStore.ListTop5(1);
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
}
