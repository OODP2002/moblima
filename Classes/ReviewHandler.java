import java.util.ArrayList;
import java.util.Scanner;

public interface ReviewHandler {
    Scanner sc = new Scanner(System.in);

    default void writeReview() {
        // Honestly have no clue how this works rn like how do i reference the movie object that the person is looking at
        // Think we should relook the UML and have a user class, which handles the purchasing, then these movieGoers are like ticket objects

        ArrayList<Movie> movieList = MovieStore.getInstance().getMovies();

        // Getting review information
        System.out.print("Enter movie ID you wish to write a review for: ");
        int movieID = sc.nextInt();
        sc.nextLine();
        // IMPLEMENT: Validate if movieID is valid


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
        String reviewDes = "";
        if (sc.hasNextLine()){
            reviewDes = sc.nextLine();
        }

        // Adding review to the correct movie object, validation should be done before calling this method in main()
        for (int i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieID() == movieID){
                // Calls addReview Method within the OverallReview class
                movieList.get(i).getOverallReviews().addReview(reviewRating, reviewDes);
            }
        }

    }
 }
