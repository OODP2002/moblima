import java.util.Scanner;

public class SysMovieModule {
    private CinemaStaff cinemaStaff;
    Scanner sc = new Scanner(System.in);

    public SysMovieModule(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }

    public void run() {
        System.out.println("-----System Movie Module-----");
        System.out.println("Select an option below (1-4):");
        System.out.println("1 - Create new movies");
        System.out.println("2 - Update movies");
        System.out.println("3 - Remove movies");
        System.out.println("4 - View all movies");
        System.out.println("5 - Quit");
        System.out.println("-----------------------------");

        System.out.print("Select an option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> cinemaStaff.createNewMovie();
            case 2 -> cinemaStaff.updateMovie();
            case 3 -> cinemaStaff.removeMovie();
            case 4 -> cinemaStaff.printAllMovies();
            case 5-> System.out.println("Exiting System Movie Module");
            default -> System.out.println("Invalid choice, exiting System Movie module");
        }
    }
}
