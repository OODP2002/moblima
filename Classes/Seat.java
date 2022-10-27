public class Seat {
    private String seatID;
    private boolean isAvail;
    private String cinemaCode; 

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
