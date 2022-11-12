import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MovieGoer implements Person, ReviewHandler, MovieQuery, TicketInterface {
    private String name;
    private String email;
    private Integer mobile;
    private HashMap<String, Ticket> tickets = new HashMap<>();  // key=TRANSACTION_ID
    Scanner sc = new Scanner(System.in);


    // Initialization: get Movie Goer details
    public MovieGoer() {
        System.out.println("Welcome to Movie Goer registration module");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        System.out.print("Enter mobile number: ");
        this.mobile = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter email address: ");
        this.email = sc.nextLine();
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public String getMobile() {
        return String.valueOf(this.mobile);
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public void buyTicket() {
        System.out.println("---Welcome to buy ticket module---");
        System.out.print("Enter MovieID (-1 to return): ");
        String movieID = sc.nextLine();
        if (movieID.equals("-1")){
            System.out.println("---Exiting buy ticket module---");
            return;
        }
        searchMovie(movieID);
        
        Set<String> keys = ShowTimeStore.getInstance().getShowTimeHashMap().keySet();
        System.out.println("-----------ShowTimes----------");
        for (String key : keys){
            // Get one showTime
            ShowTime showTime = ShowTimeStore.getInstance().getShowTime(key);
            if (Integer.valueOf(movieID) == Integer.valueOf(showTime.getMovieID())){
                System.out.println("ShowTimeID: "+ showTime.getShowtimeID());
                showTime.printShowTime();
                System.out.println("----------------------");
            }
        
        }
        
        System.out.print("Enter ShowTimeID of movie to purchase: ");
        String showtimeID = sc.nextLine();

        ShowTime curShowTime = ShowTimeStore.getInstance().getShowTime(showtimeID);

        // Check if showtime ID exists
        if (curShowTime == null) {
            System.out.println("Error: showtime does not exist!");
            System.out.println("---Exiting showtime module---");
            return;
        }

        TicketHandler ticketHandler = new TicketHandler(curShowTime);
        ticketHandler.getMovieGoerDetails(this);

        Ticket newTicket = ticketHandler.buyTicket();
        tickets.put(newTicket.getTransactionID(), newTicket);
    }
}
