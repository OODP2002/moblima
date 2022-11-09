import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TicketStore {

    //Attributes
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private String path = System.getProperty("user.dir") + ("/src/tickets.txt");
    private static TicketStore instance = new TicketStore();

    //Constructor
    private TicketStore(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                tickets.add(createTicketObj(line)); //Add pricing object to special occasion array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            //System.out.println(err.getStackTrace());
            System.out.println("Error: Credential list not found");
        }
    }

    //Operations
    //Return single instance of store 
    public static TicketStore getInstance(){
        return instance;
    }

    //Creates Ticket obj based on a line in the tickets txt file
    public Ticket createTicketObj(String info){
        String[] infoArr = info.split("\\|");
        
        String transactionID = infoArr[0]; //SeatID + YYYYMMDDhhmm
        String username = infoArr[1];
        String email = infoArr[2];
        Double mobile = Double.parseDouble(infoArr[3]);
        String seatID = infoArr[4];
        AgeGroup ageGroup;

        switch(infoArr[5]){
            case "CHILD":
                ageGroup = AgeGroup.CHILD;
                break;
            case "ADULT":
                ageGroup = AgeGroup.ADULT;
                break;
            case "SENIOR":
                ageGroup = AgeGroup.SENIOR;
                break;
            default:
                ageGroup = AgeGroup.ADULT;
            
        }
        
        return new Ticket(transactionID, username, email, mobile, seatID, ageGroup);
    }

    public void newTicket(Ticket ticket){
        this.tickets.add(ticket);
        return;
    }

    public void listPastPurchases(String username, String email, Double mobile){
        for (int i = 0; i < this.tickets.size(); i++){
            if (this.tickets.get(i).isUser(username, email, mobile)){
                System.out.println(this.tickets.get(i).toString());
            }
        }
    }   

    public void writeToTicketsFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine(); //Header row
            
            FileWriter writer = new FileWriter(path);
            writer.write(header);
            for (int i = 0; i < this.tickets.size(); i++){
                writer.write("\n" + this.tickets.get(i).toString());
            }
            writer.close();
            reader.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
 
// cineplexID: [0-9][0-9]
// cinemaID: cineplexID+ [0-9][0-9]
// showtimeID: cinemaID + [\S][\S][\S] (3 chars = 238kshowtimes possible per cinema)
// seatID: showtimeID + [A-Z][1-20]