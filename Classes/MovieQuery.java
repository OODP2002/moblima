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
        MovieStore movStore = MovieStore.getInstance();
        System.out.println("----------All Movies----------");
        movStore.printAllMovies(1);
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
            printMovieInfo(String.valueOf(movieID), 1);
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
            printMovieInfo(movieID, 1);
        }
    }

    private void printMovieInfo(String movieID, int toggle) {
        Movie movie = MovieStore.getInstance().getMovie(movieID);
        System.out.println("Movie Name: " + movie.getMovieName());
        System.out.println("Movie ID: " + movie.getMovieID());
        System.out.println("Movie Duration: " + movie.getMovieDuration());
        System.out.println("Showing Status: " + movie.getShowingStatus());
        System.out.println("Age Rating: " + movie.getAgeRating());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Viewing Mode: " + movie.getViewingMode());
        System.out.println("Movie Hype: " + movie.getMovieHype());
        if (toggle==0) System.out.println("Movie Sales: " + movie.getMovieSales());
        System.out.println("Average rating: " + movie.getOverallReviews().getAvgRating());
        movie.printMoviePersonnel();
    }
}
