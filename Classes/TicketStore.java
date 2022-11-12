import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class TicketStore {

    //Attributes
    private HashMap<String, Ticket> ticketHashMap = new HashMap<>();    // key=TRANSACTION_ID
    private String path = ("Classes/src/tickets.txt");
    private static TicketStore single_instance = null;
    private TxtReaderWriter ticketReaderWriter = new TxtReaderWriter(path);
    
    //Constructor
    private TicketStore(){
        loadTicketHashMap(ticketReaderWriter.getRawStringFromFile());
    }

    // Destructor
    public void closeTicketStore() {
        ticketReaderWriter.setRawStringFromFile(parseHashMap());
    }

    //Operations
    //Return single instance of store 
    public static TicketStore getInstance(){
        if (single_instance == null){
            single_instance = new TicketStore();
        }
        return single_instance;
    }

    public void addTicketToStore(Ticket ticket) {
        ticketHashMap.put(ticket.getTransactionID(), ticket);
    }

    public Ticket getTicketFromStore(String transactionID) {
        return ticketHashMap.get(transactionID);
    }

    public ArrayList<Ticket> getTicketWithUserDetails(String name, String email, String mobile){
        Set<String> keys = ticketHashMap.keySet();
        ArrayList<Ticket> correctTickets = new ArrayList<>();
        for (String key:keys){
            if (ticketHashMap.get(key).getUsername().equals(name) && ticketHashMap.get(key).getEmail().equals(email) && ticketHashMap.get(key).getMobile().equals(mobile)){
                correctTickets.add(ticketHashMap.get(key));
            }
        }
        return correctTickets;
    }

    private void loadTicketHashMap(ArrayList<String []> ticketRawStore) {
        if (ticketRawStore.size() == 0)
            return;

        for (String[] line: ticketRawStore) {
            Ticket ticket = new Ticket(line[0]);
            ticket.setUsername(line[1]);
            ticket.setEmail(line[2]);
            ticket.setMobile(line[3]);
            ticket.setSeatID(line[4]);
            ticket.setAgeGroup(AgeGroup.valueOf(line[5]));
            ticket.setPrice(Double.parseDouble(line[6]));
            ticketHashMap.put(line[0], ticket);
        }
    }

    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        Collection<Ticket> ticketCollection = ticketHashMap.values();

        for (Ticket ticket: ticketCollection) {
            ArrayList<String> line = new ArrayList<>();

            line.add(ticket.getTransactionID());
            line.add(ticket.getUsername());
            line.add(ticket.getEmail());
            line.add(ticket.getMobile());
            line.add(ticket.getSeatID());
            line.add(String.valueOf(ticket.getAgeGroup()));
            line.add(String.valueOf(ticket.getPrice()));

            String[] strArr = new String[line.size()];
            arrayListOut.add(line.toArray(strArr));
        }
        return arrayListOut;
    }
}