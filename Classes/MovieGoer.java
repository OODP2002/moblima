import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class MovieGoer implements PersonInterface{
    private String name;
    private String email;
    
    public MovieGoer(String n, String em, Integer mob) {
        this.name = n;
        this.email = em;
        this.mobile = mob;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Integer mobile;

    public Integer getMobile() {
        return this.mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public void writeReview(int movieID, ArrayList<Movie> movieList) {
        // Honestly have no clue how this works rn like how do i reference the movie object that the person is looking at
        // Think we should relook the UML and have a user class, which handles the purchasing, then these movieGoers are like ticket objects
        
        // Getting review information
        MovieStore movStore = MovieStore.getInstance();

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

    public void listBy() {
        // Should call from listmovie interface
        // Cannot remember if it is the movies that are watched by the moviegoer or it is all the movies, but iirc it is all movies
        // CORRECTION: IT IS LIST ALL MOVIES SO THEY KNOW WHAT EXISTS
        MovieStore movStore = MovieStore.getInstance();
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

    public void listAllMovies() {
        // Use moviestore to read all the movie objects
        MovieStore movStore = MovieStore.getInstance();
        movStore.printAllMovies();
    }

    public void searchMovie() {
        MovieStore movStore = MovieStore.getInstance();
        Scanner s = new Scanner(System.in);
        int MovieID = -1;
        System.out.println("Enter MovieID: ");
        if (s.hasNextInt()){
            MovieID = s.nextInt();
        }
        Movie movie = movStore.searchMovie(MovieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("--------------");
            movie.printInfo();
        }

    }

    public void searchMovie(int MovieID) {
        MovieStore movStore = MovieStore.getInstance();
        Movie movie = movStore.searchMovie(MovieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("--------------");
            movie.printInfo();
        }
    }

    public void showHistory(){
        // Use name attribute to match with ticketStore
        TicketStore tixStore = TicketStore.getInstance();
        System.out.println("------Past Ticket History------");
        tixStore.listPastPurchases(name, email, mobile);
    }

    public void buyTicket(int showTimeID) {
        // Need to know what is showTimeID within showtimeStore and what exactly does price handler do  
        Scanner s = new Scanner(System.in);
        int MovieID = -1;
        System.out.println("Enter MovieID: ");
        if (s.hasNextInt()){
            MovieID = s.nextInt();
        }
        this.searchMovie(MovieID);

        ShowTimeStore showStore = ShowTimeStore.getInstance();
        Set<String> keys = showStore.getShowTimeHashMap().keySet();
        ShowTime showtime;
        Movie curMovie;
        System.out.println("-------Showtimes------");
        for (String key: keys){
            // Get one showtime
            showtime = showStore.getShowTime(key);
            curMovie = showtime.getMovie();
            if (MovieID == curMovie.getMovieID()){
                System.out.println("ShowtimeID: "+showtime.getShowtimeID());
                System.out.println("MovieID: "+curMovie.getMovieID());
                System.out.println("Title: "+curMovie.getMovieName());
                showtime.printShowTime();
            }
        }
    }
}
