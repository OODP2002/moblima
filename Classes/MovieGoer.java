import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class MovieGoer implements Person{
    private String name;
    private MovieStore movStore = MovieStore.getInstance();
    private String email;
    private Integer mobile;
    Scanner sc = new Scanner(System.in);


    // Initialization: get Movie Goer details
    public MovieGoer() {
        System.out.println("Welcome to Movie Goer registration module");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        System.out.print("Enter mobile number: ");
        this.mobile = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter email address: ");
        this.email = sc.nextLine();
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


    public Integer getMobile() {
        return this.mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public void writeReview() {
        // Honestly have no clue how this works rn like how do i reference the movie object that the person is looking at
        // Think we should relook the UML and have a user class, which handles the purchasing, then these movieGoers are like ticket objects
        
        // Getting review information
        System.out.print("Enter movie ID you wish to write a review for: ");
        int movieID = sc.nextInt();
        sc.nextLine();
        // IMPLEMENT: Validate if movieID is valid

        ArrayList<Movie> movieList = MovieStore.getInstance().getMovies();

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

    public void listBy() {
        // Should call from listmovie interface
        // Cannot remember if it is the movies that are watched by the moviegoer or it is all the movies, but iirc it is all movies
        // CORRECTION: IT IS LIST ALL MOVIES SO THEY KNOW WHAT EXISTS
        Scanner sc = new Scanner(System.in);

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

    public void listAllMovies() {
        // Use moviestore to read all the movie objects
        // MovieStore movStore = MovieStore.getInstance();
        System.out.println("----------All Movies----------");
        movStore.printAllMovies(1);
    }

    public void searchMovie() {
        Scanner s = new Scanner(System.in);
        int MovieID = -1;
        System.out.println("-------Searching Movies-------");
        System.out.println("Enter MovieID (-1 to return): ");
        if (s.hasNextInt()){
            MovieID = s.nextInt();
            s.nextLine();
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

    public void searchMovie(int MovieID) {
        Movie movie = movStore.searchMovie(MovieID);
        if (movie == null){
            System.out.println("No such movie found. \n");
        }
        else {
            System.out.println("-------Movie Details-------");
            movie.printInfo(1);
        }
    }

    public void showHistory(){
        // Use name attribute to match with ticketStore
        TicketStore tixStore = TicketStore.getInstance();
        System.out.println("------Past Ticket History------");
        tixStore.listPastPurchases(name, email, mobile);
    }

    public void buyTicket() {
        // Need to know what is showTimeID within showtimeStore and what exactly does price handler do  
        Scanner s = new Scanner(System.in);
        int MovieID = -1;
        System.out.println("--------Purchase Ticket-------");
        System.out.print("Enter MovieID (-1 to return): ");
        if (s.hasNextInt()){
            MovieID = s.nextInt();
            s.nextLine();
        }

        if (MovieID == -1){
            System.out.println("return");
            return;
        }
        this.searchMovie(MovieID);

        ShowTimeStore showStore = ShowTimeStore.getInstance();
        Movie curMovie = movStore.searchMovie(MovieID);
        
        Set<String> keys = showStore.getShowTimeHashMap().keySet();
        System.out.println("");
        System.out.println("-----------ShowTimes----------");
        ArrayList<String> showTimeIDArr = new ArrayList<>();
        for (String key: keys){
            // Get one showtime
            ShowTime showtime = showStore.getShowTime(key);
            int showtimeMovieID = showtime.getMovieID();
            if (MovieID == showtimeMovieID){
                showTimeIDArr.add(showtime.getShowtimeID());
                System.out.println("ShowTimeID: "+ showtime.getShowtimeID());
                showtime.printShowTime();
                System.out.println("----------------------");
            }
        }
        System.out.println("Enter ShowTimeID: ");
        String selShowID = s.nextLine();
        if (showTimeIDArr.contains(selShowID)){
            System.out.println("---Displaying Cinema Layout---");
        }
        else {
            System.out.println("-------No Such ShowTime------");
        }
    }
}
