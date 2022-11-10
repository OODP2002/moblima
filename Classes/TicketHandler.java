import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public interface TicketHandler {
    Scanner sc = new Scanner(System.in);

    default void buyTicket() {
        // Need to know what is showTimeID within showtimeStore and what exactly does price handler do
        Scanner s = new Scanner(System.in);
        int MovieID = -1;
        System.out.println("--------Purchase Ticket-------");
        System.out.print("Enter MovieID (-1 to return): ");
        if (s.hasNextInt()){
            MovieID = s.nextInt();
            s.nextLine();
        }

        if (MovieID == -1){
            System.out.println("return");
            return;
        }

        ShowTimeStore showStore = ShowTimeStore.getInstance();
        Movie curMovie = MovieStore.getInstance().searchMovie(MovieID);

        Set<String> keys = showStore.getShowTimeHashMap().keySet();
        System.out.println("");
        System.out.println("-----------ShowTimes----------");
        ArrayList<String> showTimeIDArr = new ArrayList<>();
        for (String key: keys){
            // Get one showtime
            ShowTime showtime = showStore.getShowTime(key);
            int showtimeMovieID = showtime.getMovieID();
            if (MovieID == showtimeMovieID){
                showTimeIDArr.add(showtime.getShowtimeID());
                System.out.println("ShowTimeID: "+ showtime.getShowtimeID());
                showtime.printShowTime();
                System.out.println("----------------------");
            }
        }
        System.out.println("Enter ShowTimeID: ");
        String selShowID = s.nextLine();
        if (showTimeIDArr.contains(selShowID)){
            System.out.println("---Displaying Cinema Layout---");
        }
        else {
            System.out.println("-------No Such ShowTime------");
        }
    }
}
