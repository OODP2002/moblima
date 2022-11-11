import java.util.HashMap;

public interface TicketInterface {
    default void showHistory(HashMap<String, Ticket> tickets) {
        System.out.println("---Ticket booking history");
//        for (Ticket ticket: tickets.values()) {
//            System.out.print("Transaction ID: " + ticket.getTransactionID());
//            System.out.print("Showtime ID: " + ticket.getPrice());
//            System.out.print("Seat ID: " + ticket.getSeatID());
//        }
        for (String key: tickets.keySet()) {
            Ticket ticket = tickets.get(key);
            System.out.print("Transaction ID: " + ticket.getTransactionID());
//            System.out.print("Showtime ID: " + ticket.getPrice());
            System.out.print("Seat ID: " + ticket.getSeatID());
        }
    }
}
