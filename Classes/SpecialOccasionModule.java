import java.util.Scanner;

public class SpecialOccasionModule {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public SpecialOccasionModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
        System.out.println("Special Occasion Module");
        System.out.println("Select an option below (1-5):");
        System.out.println("1 - Create new special occasion ");
        System.out.println("2 - Update special occasion");
        System.out.println("3 - Remove speical occasion ");
        System.out.println("4 - Print all special occasions");
        System.out.println("5 - Quit");
        System.out.println("-----------------------------");
        System.out.print("Select an option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> cinemaStaff.addSpecialOccasion();
            case 2 -> cinemaStaff.updateSpecialOccasion();
            case 3 -> cinemaStaff.removeSpecialOccasion();
        }
    }
}
