import java.util.Scanner;

public class TicketPricingModule {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public TicketPricingModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
        System.out.println("\nWelcome to Ticket Pricing Module");
        System.out.println("-----------------------------");

        int choice = 0;
        while (choice != 5) {
            System.out.println("Select an option below (1-4):");
            System.out.println("1 - Create new pricing rule");
            System.out.println("2 - Update new pricing rule");
            System.out.println("3 - Remove pricing");
            System.out.println("4 - Print all pricings");
            System.out.println("5 - Quit");
            System.out.println("-----------------------------\n");
            System.out.print("Select an option: ");

            try{
                choice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 5).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1 -> cinemaStaff.addPricingRule();
                case 2 -> cinemaStaff.updatePricingRule();
                case 3 -> cinemaStaff.removePricingRule();
                case 4 -> cinemaStaff.printAllPricingRules();
                case 5-> System.out.println("Exiting Ticket Pricing Module");
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
