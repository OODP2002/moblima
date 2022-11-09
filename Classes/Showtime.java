import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Showtime {
    private String showtimeID; //Todo: Allocate dynamically after integrating src files
    private String movieID;
    private String movieName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private View view; 
    private boolean isBlockbuster; 
    private int numEmptySeats;
    private ArrayList<Seat> seats; 

    public Showtime(String showtimeID, String movieID, String movieName, LocalDateTime startTime, LocalDateTime endTime,  View view, boolean isBlockbuster, int numEmptySeats){
        this.showtimeID = showtimeID; 
        this.movieID = movieID;
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.view = view; 
        this.isBlockbuster = isBlockbuster; 
        this.numEmptySeats = numEmptySeats;
    }

    public void displaySeats(){
        for (int i = 0; i  < this.seats.size(); i++){
            System.out.println(String.format("Seat %d is%s available", seats.get(i).getSeatID(), seats.get(i).getAvail()? "" : " not"));
        }
    }
    public void bookSeat(String seatID){
        for (int i = 0; i < this.seats.size(); i++){
            
            if (seats.get(i).getSeatID() == seatID && seats.get(i).getAvail()){
                seats.get(i).setAvail(false); // seat no longer available
            } else {
                System.out.println("Unable to book seat. Try again.");
            }
        }
    }
    public void emptySeat(String seatID){
        for (int i = 0; i < this.seats.size(); i++){
            
            if (seats.get(i).getSeatID() == seatID && !seats.get(i).getAvail()){
                seats.get(i).setAvail(true); // seat no longer available
            } else {
                System.out.println("Unable to empty seat. Try Again.");
            }
        }
    }
    public void displayShowtime(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatStartTime = this.startTime.format(format);  
        String formatEndTime = this.endTime.format(format);  
        String vType=""; 
        switch(this.view){
            case _2D: 
                vType = "2D";
                break;
            case _3D:
                vType = "3D";
                break;
        }
        System.out.println(String.format("%s | %s | %s | %s | %s | %s | %d", this.showtimeID, this.movieID, this.movieName,formatStartTime, formatEndTime, vType, this.numEmptySeats ));
    }

}