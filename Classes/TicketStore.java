import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TicketStore {

    //Attributes
    private HashMap<String, Ticket> ticketHashMap = new HashMap<>();    // key=TRANSACTION_ID
    private String path = ("Classes/src/tickets.txt");
    private static TicketStore instance = new TicketStore();
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
        return instance;
    }


    public void addTicketToStore(Ticket ticket) {
        ticketHashMap.put(ticket.getTransactionID(), ticket);
    }

    public Ticket getTicketFromStore(String transactionID) {
        return ticketHashMap.get(transactionID);
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
            ticket.setPrice(Long.getLong(line[6]));
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