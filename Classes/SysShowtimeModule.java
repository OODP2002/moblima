import java.util.Scanner;

public class SysShowtimeModule {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public SysShowtimeModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
        System.out.println("Welcome to System Showtime Module");
        System.out.println("Select an option below (1-4):");
        System.out.println("1 - Create new showtime");
        System.out.println("2 - Update showtime");
        System.out.println("3 - Remove showtime");
        System.out.println("4 - Quit");
        System.out.println("-----------------------------");
        System.out.print("Select an option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> cinemaStaff.addShowTime();
            case 2 -> cinemaStaff.updateShowTime();
            case 3 -> cinemaStaff.removeShowTime();
            case 4-> System.out.println("Exiting System Showtime Module");
            default -> System.out.println("Invalid choice, exiting System Showtime module");
        }
    }
}