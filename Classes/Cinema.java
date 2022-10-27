import java.util.ArrayList;

public class Cinema{
    
    //layout
    private int row; 
    private int columns; 
    private int numSeats;

    private ArrayList<Showtime> showtimes = new ArrayList<Showtime>(); // TODO: take in showtime.txt data
    private String cinemaID;  

    public Cinema (int row, int columns, String cinemaID){
        this.row = row; 
        this.columns = columns;
        this.numSeats = row * columns;
        this.cinemaID = cinemaID;
    }

    public int getRow(){
        return this.row;
    }

    public void setRow(int newRow){
        this.row = newRow; 
        this.numSeats =  newRow * this.columns; 
    }

    public int getCol(){
        return this.columns;
    }

    public void setCol(int newCol){
        this.columns = newCol;
        this.numSeats = this.row * newCol; //Assumes admin is only able to change size of cinema when cinema is not booked 
    }

    public int getNumSeats(){
        return this.numSeats;
    }

    public ArrayList<Showtime> getShowtimes(){
        System.out.println("Movie ID | Movie Name | Start Time | End Time | Visual Type | Seats Available");
        for (int i = 0; i < showtimes.size(); i++){
            showtimes.get(i).displayShowtime();
        }
        return showtimes; 
    }

    public void setShowtime(Showtime newShowtime){
        this.showtimes.add(newShowtime);
    }

}