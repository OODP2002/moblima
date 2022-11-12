import java.util.ArrayList;

public interface TicketInterface {
    default void showHistory(ArrayList<Ticket> tickets) {
        System.out.println("---Ticket booking history---");
//        for (Ticket ticket: tickets.values()) {
//            System.out.print("Transaction ID: " + ticket.getTransactionID());
//            System.out.print("Showtime ID: " + ticket.getPrice());
//            System.out.print("Seat ID: " + ticket.getSeatID());
//        }
        for (int i=0;i<tickets.size();i++) {
            Ticket ticket = tickets.get(i);
            System.out.printf("%2d. ",i+1);
            System.out.printf("Transaction ID: %-30s\n",ticket.getTransactionID());
            System.out.printf("    Price: %-5s Seat: %-5s Age: %-10s\n",ticket.getPrice(),ticket.getSeatID(),ticket.getAgeGroup());
        }

    }
}
