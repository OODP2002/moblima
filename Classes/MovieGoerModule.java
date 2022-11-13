import java.util.Scanner;
/**
 * A boundary object.
 * 
 * The menu which the user will see upon login
 * 
 * @author Marc
 * @version 1.0.0 Nov 12, 2022
 */
public class MovieGoerModule {
    /**
     * Scanner to receive input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Displays the menu and redirects user
     */
    public void run() {
        MovieGoer movieGoer = new MovieGoer();
        int choice = -1;
        do {
            System.out.println("\n----Customer Panel---");
            System.out.println("(1) List all movies");
            System.out.println("(2) List top 5 movies"); // This one depends on admin setting to determine which is top 5
            System.out.println("(3) Search for movie to view details"); // including showtimes, reviews and ratings
            System.out.println("(4) Review movie");
            System.out.println("(5) Buy ticket");
            System.out.println("(6) Display ticket history");
            System.out.println("(7) Quit");
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
                case 1 -> movieGoer.listAllMovies();
                case 2 -> movieGoer.listBy();
                case 3 -> movieGoer.searchMovie();
                case 4 -> movieGoer.writeReview();
                case 5 -> movieGoer.buyTicket();
                case 6 -> movieGoer.showHistory(movieGoer.getTickets());
                case 7 -> System.out.println("Exiting customer module...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }
}
