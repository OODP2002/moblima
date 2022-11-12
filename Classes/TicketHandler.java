import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TicketHandler {
    private String seatID;
    private Ticket ticket;
    private AgeGroup ageGroup;
    private ShowTime showTime;
    private Movie movie;
    private Seat seat;
    Scanner sc = new Scanner(System.in);

    public TicketHandler(ShowTime showtime) {
        
        // Setting Showtime
        this.showTime = showtime;
        
        // Print showtime layout
        ShowTimeLayout showTimeLayout = showTime.getShowTimeLayout();
        showTimeLayout.printLayout();
        
        System.out.println(showtime.getShowtimeID() + " selected. Choose your seats (A1,B15...) ");
        getMovie();
        // Get seat and check if seat is available
        do {
            this.seatID = sc.nextLine();
            this.seat = showTimeLayout.getSeat(seatID);

            if (seat == null)
                System.out.println("Seat is not available, please try again!");

        } while (seat == null);
        
        // Set Seat
        showTimeLayout.getSeat(seatID).setAvail(false);
        
        // Get age group
        do {
            getAgeGroup();
        } while (ageGroup == null);
        
        // Generate ticket object
        ticket = new Ticket(generateTransactionID(showtime.getShowtimeID()));
        
        // Set seat and AgeGroup
        ticket.setSeatID(seatID);
        ticket.setAgeGroup(ageGroup);

        // Query price
        ticket.setPrice(getPrice());
        System.out.println("Ticket costs $" + ticket.getPrice());

        System.out.println("Ticket purchase successful!");
        System.out.println("Transaction ID: " + ticket.getTransactionID());
    }

    public Ticket buyTicket() {
        return ticket;
    }

    public void getMovieGoerDetails(MovieGoer movieGoer) {
        // Get movie goer details
        this.ticket.setMobile(movieGoer.getMobile());
        this.ticket.setEmail(movieGoer.getEmail());
        this.ticket.setUsername(movieGoer.getName());
    }

    // Generate transactionID in format CCCCSSSSSSyyyyMMddHHmm
    private String generateTransactionID(String showtimeID) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return showtimeID.concat(seatID).concat(LocalDateTime.now().format(formatter));
    }


    // Get age group from user
    private void getAgeGroup() {
        System.out.println("Enter type of ticket you wish to buy: ");
        System.out.println("(1) Child ticket");
        System.out.println("(2) Adult ticket");
        System.out.println("(3) Senior ticket");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
                case 1 -> this.ageGroup = AgeGroup.CHILD;
                case 2 -> this.ageGroup = AgeGroup.ADULT;
                case 3 -> this.ageGroup = AgeGroup.SENIOR;
                default -> System.out.println("Invalid choice!");
        }
    }

    // Get movie infg
    private void getMovie() {
        String movieID = showTime.getMovieID();
        this.movie = MovieStore.getInstance().getMovieHashMap().get(movieID);
    }

    private double getPrice() {
        Hype hype = movie.getMovieHype();
        CinemaClass cinemaClass = showTime.getCinemaClass();
        boolean isPH = SpecialOccasionStore.getInstance().isSpecialOccasion(LocalDate.from(showTime.getStartTime()));
        Integer dayOfWeek = showTime.getStartTime().getDayOfWeek().getValue();
        LocalTime startTime = showTime.getStartTime().toLocalTime();
        View view = movie.getViewingMode();

        return PricingStore.getInstance().queryPrice(ageGroup, hype, cinemaClass, isPH, dayOfWeek, startTime, view);
    }
}
