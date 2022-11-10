import java.util.Scanner;

public class CustomerModule {
    Scanner sc = new Scanner(System.in);
    private static CustomerModule single_instance = null;

    private CustomerModule() {
        int choice = 0;
        MovieGoer movieGoer = new MovieGoer();

        while (choice != 7){
            System.out.println("----Customer Panel---");
            System.out.println("(1) List all movies");
            System.out.println("(2) List top 5 movies"); // This one depends on admin setting to determine which is top 5
            System.out.println("(3) Search for movie to view details"); // including showtimes, reviews and ratings
            System.out.println("(4) Review movie");
            System.out.println("(5) Buy ticket");
            System.out.println("(6) Display ticket history");
            System.out.println("(7) Quit");
            System.out.println("---------------------");
            System.out.print("Choice: ");
            if (sc.hasNextInt()){
                choice = sc.nextInt();
            }
            switch(choice) {
                case 1:
                    // Call listAllMovies, data from movie store
                    movieGoer.listAllMovies();
                    break;

                case 2:
                    // Call listBy, data from movie store
                    // List by default must show both types of top 5
                    // List will only show one if admin chooses to opt out of one
                    movieGoer.listBy();
                    break;

                case 3:
                    // Call searchMovie, data from movie store
                    movieGoer.searchMovie();
                    break;

                case 4:
                    // Call writeReview, should be under movieGoer
                    movieGoer.writeReview();
                    break;

                case 5:
                    // Call buyTicket, should be under movieGoer
                    // Must have checkAvailability fn
                    // must be able to select more than one seat
                    // Must store customer's c name, email, mobile address
                    // Must create ticketID (TID)of the format XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
                    movieGoer.buyTicket();
                    break;

                case 6:
                    // Call showHistory, should be under movieGoer
                    movieGoer.showHistory();
                    break;

                case 7:
                    // Quit
                    System.out.println("Exiting customer module...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static CustomerModule getInstance() {
        if (single_instance == null)
            single_instance =  new CustomerModule();

        return single_instance;
    }
}
