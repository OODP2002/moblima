import java.util.Scanner;

public class SysListingOptionsModule {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public SysListingOptionsModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
        System.out.println("Welcome to System Listing options Module");
        System.out.println("Select an option below (1-4):");
        System.out.println("1 - Allow both listing options");
        System.out.println("2 - List by sales only");
        System.out.println("3 - List by reviews only");
        System.out.println("4 - Quit");
        System.out.println("-----------------------------");
        System.out.print("Select an option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
//            case 1 -> cinemaStaff.newPricingRule();
//            case 2 -> cinemaStaff.removePricingRule();
//            case 3 -> cinemaStaff.printAllPricingRules();
            case 4-> System.out.println("Exiting system listing options Module");
            default -> System.out.println("Invalid choice, exiting system listing options module");
        }
    }
}
