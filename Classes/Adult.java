// Done by Marc

public class Adult extends MovieGoer implements TicketPurchaseInterface{
    public Adult(String n){
        super(n);
    }
    
    public void buyTicket() {
        // Need to understand how to use pricing store, looking at the code cant understand the isPricing method and what exactly should be called to get the ticket prices for the person
        // Am guessing that the pricing requries all the isPricing fields, but that would require the buy ticket to be called outside of the movieGoer subclasses, as the information about the movie is unknown
        
    }  
}
