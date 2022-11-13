import java.util.Scanner;

public class GuestModule implements Module{
    Scanner sc = new Scanner(System.in);
    public void run() {
        Guest guest = new Guest();
        int choice = -1;
        do {
            System.out.println("----Guest Panel---");
            System.out.println("(1) List all movies");
            System.out.println("(2) List top 5 movies"); // This one depends on admin setting to determine which is top 5
            System.out.println("(3) Search for movie to view details"); // including showtimes, reviews and ratings
            System.out.println("(4) Quit");
            System.out.println("---------------------");
            System.out.print("Choice: ");

            try{
                choice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 4).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1 -> guest.listAllMovies();
                case 2 -> guest.listBy();
                case 3 -> guest.searchMovie();
                case 4 -> System.out.println("Exiting guest module...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
