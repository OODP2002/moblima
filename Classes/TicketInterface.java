import java.util.HashMap;

public interface TicketInterface {
    default void showHistory(HashMap<String, Ticket> tickets) {
        System.out.println("---Ticket booking history");
        for (Ticket ticket: tickets.values()) {
            System.out.print("Transaction ID: " + ticket.getTransactionID());
            System.out.print("Showtime ID: " + ticket.getShowTimeID());
            System.out.print("Seat ID: " + ticket.getSeatID());
        }
    }
}
