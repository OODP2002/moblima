import java.util.Scanner;
/**
 * This represents the MovieGoer Module which will be accessed when the user selects MovieGoer from the main menu in MOBLIMA
 * 
 * Upon succesful login under MovieGoer, the user will be presented with options available to Guest user, as well as the additional option to buy a ticket
 * 
 * @author Marc Chern
 * @version 1.0.0 Nov 12, 2022
 */
public class MovieGoerModule implements Module{
    /**
     * Scanner to receive input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Displays the menu and redirects user
     */
    public void run() {
        MovieGoer movieGoer = new MovieGoer();
        int choice;

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

            choice = sc.nextInt();
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
