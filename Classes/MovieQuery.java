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

        int MovieID = -1;
        System.out.println("-------Searching Movies-------");
        System.out.println("Enter MovieID (-1 to return): ");
        if (sc.hasNextInt()){
            MovieID = sc.nextInt();
            sc.nextLine();
        }
        if (MovieID == -1){
            return;
        }
        Movie movie = movStore.searchMovie(MovieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            movie.printInfo(1);
        }

    }

    default void searchMovie(int MovieID) {
        MovieStore movStore = MovieStore.getInstance();
        Movie movie = movStore.searchMovie(MovieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            movie.printInfo(1);
        }
    }
}
