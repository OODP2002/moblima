public class Seat {
    private String seatID;  // eg.A1, [row][column]
    private boolean isAvail;

    public Seat(String seatID) {
        this.seatID = seatID;
        this.isAvail = true;
    }

    public void setAvail(boolean avail){
        this.isAvail = avail; 
    }

    public boolean getAvail(){
        return this.isAvail;
    }
    
    public String getSeatID(){
        return this.seatID;
    }

}
