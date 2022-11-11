import java.util.Scanner;

public class MovieGoerModule {
    Scanner sc = new Scanner(System.in);
    private static MovieGoerModule single_instance = null;

    private MovieGoerModule() {
        MovieGoer movieGoer = new MovieGoer();

        int choice;
        do {
            System.out.println("----Customer Panel---");
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

    public static MovieGoerModule getInstance() {
        if (single_instance == null)
            single_instance =  new MovieGoerModule();

        return single_instance;
    }
}
