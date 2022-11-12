import java.util.Scanner;

public class TicketPricingModule {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public TicketPricingModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
        System.out.println("Welcome to Ticket Pricing Module");
        System.out.println("Select an option below (1-4):");
        System.out.println("1 - Create new pricing rule");
        System.out.println("2 - Remove pricing");
        System.out.println("3 - Print all pricings");
        System.out.println("4 - Quit");
        System.out.println("-----------------------------");
        System.out.print("Select an option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> cinemaStaff.addPricingRule();
            case 2 -> cinemaStaff.removePricingRule();
            case 3 -> cinemaStaff.printAllPricingRules();
            case 4-> System.out.println("Exiting Ticket Pricing Module");
            default -> System.out.println("Invalid choice, exiting ticket pricing module");
        }
    }
}
