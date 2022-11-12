import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public interface ReviewHandler {
    Scanner sc = new Scanner(System.in);

    default void writeReview() {
        // Getting review information
        System.out.print("Enter movieID to write a review for (-1 to return): ");
        String movieID = sc.nextLine();
        if (movieID.equals("-1")){
            return;
        }

        // Review Rating
        System.out.println("Enter Movie Rating (1 - 5 Stars): ");
        int reviewRating = 0;
        reviewRating = sc.nextInt();
        sc.nextLine();

        while (reviewRating > 5 && reviewRating < 1){
            // Checks for valid rating
            System.out.println("Invalid rating, please try again. \n");
            System.out.println("Enter Movie Rating (1 - 5 Stars): ");
            if (sc.hasNextInt()){
                reviewRating = sc.nextInt();
            }
        }
        // Review Description
        System.out.println("Enter Review Description: \n");
        String reviewDes = sc.nextLine();

        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        movies.get(movieID).getOverallReviews().addReview(reviewRating, reviewDes);
        System.out.println("Exiting review module...");
    }
 }
