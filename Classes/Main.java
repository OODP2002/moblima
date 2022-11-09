import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create vendor
        // Vendor vendor = new Vendor();
        // vendor.addCineplex();
        // System.out.println(vendor.getCineplexHashMap().get("00").getCineplexName());

        System.out.println("Starting app...");
        int loginChoice = 0;
        Scanner s = new Scanner(System.in);

        while(loginChoice != 3){
            System.out.println("-----Login Panel-----");
            System.out.println("(1) Admin");
            System.out.println("(2) Customer");
            System.out.println("(3) Quit");
            System.out.println("---------------------");
            System.out.print("Choice: ");

            if (s.hasNextInt()){
                loginChoice = s.nextInt();
            }

            switch(loginChoice) {
                case 1:
                    // Call admin logic
                    adminModule();
                    break;
                case 2:
                    // Call user logic
                    custModule();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invallid Choice.");
            }
        }
    }

    public static void custModule() {
        String name = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Enter name: ");
        name = s.nextLine();

        // Menu of choices the customer can choose from
        int choice = 0;
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
            if (s.hasNextInt()){
                choice = s.nextInt();
            }
            switch(choice) {
                case 1:
                    // Call listAllMovies, data from movie store
                    break;
                case 2:
                    // Call listBy, data from movie store
                    // List by default must show both types of top 5
                    // List will only show one if admin chooses to opt out of one
                    break;
                case 3:
                    // Call searchMovie, data from movie store
                    break;
                case 4:
                    // Call writeReview, should be under movieGoer
                    break;
                case 5:
                    // Call buyTicket, should be under movieGoer
                    // Must have checkAvailability fn
                    // must be able to select more than one seat
                    // Must store customer's name, email, mobile address
                    // Must create ticketID (TID)of the format XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
                    break;
                case 6:
                    // Call showHistory, should be under movieGoer
                    break;
                case 7:
                    // Quit
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void adminModule() {
        boolean loggedIn = false;
        boolean valid = false;
        String username;
        String password;
        Scanner s = new Scanner(System.in);
        // Login Admin
        while (!loggedIn){
            System.out.println("Enter username: ");
            username = s.nextLine();
            System.out.println("Enter password: ");
            password = s.nextLine();
            // Verify using proper login handler
            valid = true;
            // if correct, set loggedIn to be true
            if (valid) {
                System.out.println("Logging in...");
                loggedIn = true;
            }
            else {
                System.out.println("Invalid credentials.");
            }
        }

        // Menu of choices the customer can choose from
        int choice = -1;
        while (choice != 0){
            System.out.println("-----Admin Panel-----");
            System.out.println("(1) Create Movie Listing");
            System.out.println("(2) Update Movie Listing");
            System.out.println("(3) Remove Movie Listing");
            System.out.println("(4) Create Movie Showtime");
            System.out.println("(5) Update Movie Showtime");
            System.out.println("(6) Remove Movie Showtime");
            System.out.println("(7) Edit System Settings"); // This includes Ticket Prices, Holidays, Which Top 5 List to exclude (Might need to store in Store)
            System.out.println("(8) Show Top 5 Movies");
            System.out.println("(9) Show All Movies");
            System.out.println("(0) Quit");
            System.out.println("---------------------");
            System.out.print("Choice: ");
            if (s.hasNextInt()){
                choice = s.nextInt();
            }
            switch(choice) {
                case 1:
                    // Create new movie listing, setting all the required fields
                    break;
                case 2:
                    // Must be able to update all the fields that were previously set in option 1
                    break;
                case 3:
                    // Choose a movie to set to End_Of_Showing
                    // This means will not show up in list of movies for the customer as well
                    break;
                case 4:
                    // Create Movies showtime within a cinema
                    break;
                case 5:
                    // Update the showtime timings
                    break;
                case 6:
                    // Remove a particular showtime timing
                    break;
                case 7:
                    // Edit system settings
                    break;
                case 8:
                    // Show Top 5 movies in both formats
                    // This includes Ticket Prices, Holidays, Which Top 5 List to exclude (Might need to store in Store)
                    break;
                case 9:
                    // Show all movies, including those which are end of showing
                    break;
                case 0:
                    // Quit
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
