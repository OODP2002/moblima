// Done by Marc

import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoer implements PersonInterface{
    private String name;

    public MovieGoer(String n) {
        this.name = n;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void writeReview(int movieID, ArrayList<Movie> movieList) {
        // Honestly have no clue how this works rn like how do i reference the movie object that the person is looking at
        // Think we should relook the UML and have a user class, which handles the purchasing, then these movieGoers are like ticket objects
        
        // Getting review information
        Scanner sc = new Scanner(System.in);
        // Review Rating
        System.out.println("Enter Movie Rating (1 - 5 Stars): ");
        int reviewRating = 0;
        if (sc.hasNextInt()){
            reviewRating = sc.nextInt();
        }
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
        sc.close();

        // Adding review to the correct movie object, validation should be done before calling this method in main()
        for (int i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieID() == movieID){
                // Calls addReview Method within the OverallReview class
                movieList.get(i).getOverallReviews().addReview(reviewRating, reviewDes);
            }
        }

    }

    public void listMovies() {
        // Should call from listmovie interface
        // Cannot remember if it is the movies that are watched by the moviegoer or it is all the movies, but iirc it is all movies
        // CORRECTION: IT IS LIST ALL MOVIES SO THEY KNOW WHAT EXISTS
        Scanner sc = new Scanner(System.in);

        System.out.println("List movies by: \n");
        System.out.println("(1) Sales \n");
        System.out.println("(2) Reviews \n");
        System.out.println("Enter choice: ");

        int choice = 0;
        if (sc.hasNextInt()){
            choice = sc.nextInt();
        }
        sc.close();

        switch(choice) {
            case 1:
                // call movieStore listBySales method...?
                break;
            case 2:
                // call movieStore listByReviews method...?
                break;
            default:
                System.out.println("Invalid Choice");
        }

    }

    public int searchMovie(int movieID, ArrayList<Movie> movieList) {
        // CHANGE UML TO RETURN int NOT MOVIE OBJ

        // Input: movieName string
        // Output: Cineplexes, showtimes, cinemas etc. 
        for (int i=0;i<movieList.size();i++){
            if (movieList.get(i).getMovieID() == movieID){
                return movieList.get(i).getMovieID;
            }
        }
        System.out.println("No such movie found. \n");
        return -1;
    }
}
