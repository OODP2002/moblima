import java.util.Scanner;

public class AdminModule {
    Scanner sc = new Scanner(System.in);

    public void run() {
        LoginHandler loginHandler = new LoginHandler();
        CinemaStaff cinemaStaff = loginHandler.login();

        // Login fails
        if (cinemaStaff == null) {
            System.out.println("Login failed, exiting admin module now");
            return;
        }

        // Initialize modules
        TicketPricingModule ticketPricingModule = new TicketPricingModule(cinemaStaff);
        SpecialOccasionModule specialOccasionModule = new SpecialOccasionModule(cinemaStaff);

        // Menu of choices the customer can choose from
        int choice = -1;
        while (choice != 0) {
            System.out.println("-----Admin Panel-----");
            System.out.println("Option Available: (1-5):");
            System.out.println("1 - Ticket pricing ");
            System.out.println("2 - Special occasion");
            System.out.println("3 - Movies");
            System.out.println("4 - Showtimes");
            System.out.println("5 - Listing Options (System settings)");
            System.out.println("6 - Quit Admin Module");
            System.out.println("---------------------");
            System.out.print("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> ticketPricingModule.run();
                case 2 -> specialOccasionModule.run();
            }
//            switch (choice) {
//                case 1:
//                    // Create new movie listing, setting all the required fields
//                    break;
//                case 2:
//                    // Must be able to update all the fields that were previously set in option 1
//                    break;
//                case 3:
//                    // Choose a movie to set to End_Of_Showing
//                    // This means will not show up in list of movies for the customer as well
//                    break;
//                case 4:
//                    // Create Movies showtime within a cinema
//                    cinemaStaff.addShowTime();
//                    break;
//
//                case 5:
//                    // Update the showtime timings
//                    cinemaStaff.updateShowTime();
//                    break;
//
//                case 6:
//                    // Remove a particular showtime timing
//                    cinemaStaff.removeShowTime();
//                    break;
//                case 7:
//                    // Edit system settings
//                    // Change pricing, holidays, list by
//                    break;
//                case 8:
//                    // Show Top 5 movies in both formats
//                    // This includes Ticket Prices, Holidays, Which Top 5 List to exclude (Might need to store in Store)
////                    cinemaStaff.editPricing();
//                    break;
//                case 9:
//                    // Show all movies, including those which are end of showing
//                    cinemaStaff.printAllMovies();
//                    break;
//                case 0:
//                    // Quit
//                    System.out.println("Exiting admin module...");
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//            }
        }
    }
}
