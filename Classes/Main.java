import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
         // Create vendor
        Vendor vendor = new Vendor();

        System.out.println("Starting app...");
        System.out.println("Welcome to " + vendor.getVendorName() + "Movie Booking System");
        int loginChoice = 0;

        while(loginChoice != 3){
            System.out.println("-----Login Panel-----");
            System.out.println("(1) Admin");
            System.out.println("(2) Customer");
            System.out.println("(3) Quit");
            System.out.println("---------------------");
            System.out.print("Choice: ");

            if (sc.hasNextInt()){
                loginChoice = sc.nextInt();
            }

            switch(loginChoice) {
                case 1:
                    // Call admin logic
                    AdminModule.getInstance();
                    adminModule();
                    break;
                case 2:
                    // Call user logic
                    CustomerModule.getInstance();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }


    private static void adminModule() {
        LoginHandler loginHandler = new LoginHandler();
        CinemaStaff cinemaStaff = loginHandler.login();

        // Login fails
        if (cinemaStaff == null) {
            System.out.println("Login failed, exiting admin module now");
            return;
        }

        // Menu of choices the customer can choose from
        int choice = -1;
        while (choice != 0) {
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
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            }
            switch (choice) {
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
                    cinemaStaff.addShowTime();
                    break;

                case 5:
                    // Update the showtime timings
                    cinemaStaff.updateShowTime();
                    break;

                case 6:
                    // Remove a particular showtime timing

                    break;
                case 7:
                    // Edit system settings
                    // Change pricing, holidays, list by
                    break;
                case 8:
                    // Show Top 5 movies in both formats
                    // This includes Ticket Prices, Holidays, Which Top 5 List to exclude (Might need to store in Store)
//                    cinemaStaff.editPricing();
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
