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
        SysMovieModule sysMovieModule = new SysMovieModule(cinemaStaff);
        SysShowtimeModule sysShowtimeModule = new SysShowtimeModule(cinemaStaff);
        SysListingOptionsModule sysListingOptionsModule = new SysListingOptionsModule(cinemaStaff);

        // Menu of choices the customer can choose from
        int choice = -1;
        while (choice != 0) {
            System.out.println("-----Admin Panel-----");
            System.out.println("Option Available: (1-6):");
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
                case 3 -> sysMovieModule.run();
                case 4 -> sysShowtimeModule.run();
                case 5 -> sysListingOptionsModule.run();
                case 6 -> System.out.println("Signing out of admin module...");
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
