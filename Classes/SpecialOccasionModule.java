import java.util.Scanner;

public class SpecialOccasionModule implements Module {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public SpecialOccasionModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
      
        int choice = -1;
        while (choice != 4) {
            System.out.println("\n----Special Occasion Module----");
            System.out.println("Select an option below (1-4):");
            System.out.println("1 - Add new special occasion ");
            System.out.println("2 - Remove special occasion ");
            System.out.println("3 - Print all special occasions");
            System.out.println("4 - Quit");
            System.out.println("-------------------------------\n");
            System.out.print("Select an option: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error: please enter (1-4)");
                sc.nextLine();
                continue;
            }
            sc.nextLine();


            switch (choice) {
                case 1 -> cinemaStaff.addSpecialOccasion();
                case 2 -> cinemaStaff.removeSpecialOccasion();
                case 3 -> cinemaStaff.printSpecialOccasions();
                case 4 -> System.out.println("Exiting Special Occasion Module...");
                default -> System.out.println("Invalid choice, please enter (1-4)");
            }
        }
    }
}
