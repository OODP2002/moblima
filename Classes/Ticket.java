public class Ticket {
    private String transactionID;
    private String username;
    private String email;
    private Double mobile;
    private String seatID;

    Ticket(String transactionID, String username,String email,Double mobile,String seatID){
        this.transactionID = transactionID;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.seatID = seatID;
    }

    public String getTID(){
        return this.transactionID;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public Double getMobile(){
        return this.mobile;
    }

    public Double getSeatID(){
        return this.seatID;
    }

    public String toString(){
        String mobileStr = Double.toString(this.mobile);
        return this.transactionID + "|" + this.username + "|" + this.email + "|" + mobileStr + "|" + this.seatID  + "|";
    }
}   
