import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
/**
 * A Movie-Goer object.
 * The class will implement the interfaces Person, ReviewHandler, MovieQuery and TicketInterface
 * 
 * @author Marc
 * @version 1.0.0 Nov 12, 2022
 */
public class MovieGoer implements Person, ReviewHandler, MovieQuery, TicketInterface {
    /**
     * Movie-Goer Details are stored as private attributes
     * The Movie-Goer's tickets are stored within an ArrayList
     */
    private String name;
    private String email;
    private String mobile;
    private ArrayList<Ticket> tickets; // key=TRANSACTION_ID
    Scanner sc = new Scanner(System.in);

    /**
     * Default Movie-Goer Constructor
     */
    public MovieGoer() {
        System.out.println("Welcome to Movie Goer registration module");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        System.out.print("Enter mobile number: ");
        this.mobile = sc.nextLine();

        System.out.print("Enter email address: ");
        this.email = sc.nextLine();
        setTickets();
    }
    
    /**
     * Gets Movie-Goer's name
     * @return the name of the Movie-Goer
     */
    public String getName() {
        return this.name;
    }
    /**
     * Gets Movie-Goer's email
     * @return the email of the Movie-Goer
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Gets Movie-Goer's mobile number
     * @return the mobile number of the Movie-Goer
     */
    public String getMobile() {
        return this.mobile;
    }
    /**
     * Gets all the tickets which the Movie-Goer has bought previously
     * @return an ArrayList of all the Ticket objects
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    /**
     * Sets the name of the Movie-Goer
     * @param n - name of the Movie-Goer to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the email of the Movie-Goer
     * @param email - email of the Movie-Goer to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Sets the mobile number of the Movie-Goer
     * @param mobile - mobile number of the Movie-Goer
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * Sets all the previous tickets that the Movie-Goer bought in the past
     */
    public void setTickets(){
        this.tickets = TicketStore.getInstance().getTicketWithUserDetails(name, email, mobile);
    }
    /**
     * The buy ticket module
     * Movie-Goer will be able to pick a movie and purchase a movie ticket for the movie
     */
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
        // Handles ticket purchasing
        TicketHandler ticketHandler = new TicketHandler(curShowTime);
        ticketHandler.getMovieGoerDetails(this);
        // New ticket object to be added to TicketStore
        Ticket newTicket = ticketHandler.buyTicket();
        TicketStore.getInstance().addTicketToStore(newTicket);
        // Add new ticket to Movie-Goer object
        tickets.add(newTicket);
    }
}
