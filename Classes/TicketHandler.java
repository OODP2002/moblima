public class TicketHandler {
    private String showtimeID;      // showtimeID is always valid
    public TicketHandler(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public Ticket buyTicket() {
        return new Ticket("213");
    }
}
