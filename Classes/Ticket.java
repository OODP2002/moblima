import java.time.LocalDateTime;

public class Ticket {
    private String transactionID;
    private String showTimeID;
    private String seatID;
    private String username;
    private String email;
    private String mobile;
    private AgeGroup ageGroup;
    private Float price; 

    //When reading a ticket from library
    public Ticket(String transactionID, String username, String email, Integer mobile, String seatID, AgeGroup ageGroup, float price){
        this.transactionID = transactionID;
    }

    //When user creates a ticket
    public Ticket(String username, String email, Integer mobile, String seatID, AgeGroup ageGroup){
        this.username = username;
        this.email = email;
        this.mobile = String.valueOf(mobile);
        this.seatID = seatID;
        this.ageGroup = ageGroup;

        //TransactionID generation
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = doubleDigit(now.getDayOfMonth());
        String day = doubleDigit(now.getDayOfMonth());
        String hour = doubleDigit(now.getHour());
        String minute = doubleDigit(now.getMinute());
        this.transactionID = seatID + year + month + day + hour + minute; 

        //Price 
        this.price = PricingHandler.getInstance().queryPrice(this.transactionID, this.ageGroup);
    }

    private String doubleDigit(int val){
        return val < 10 ? "0" : "" + String.valueOf(val);
    }

    public String getTransactionID(){
        return this.transactionID;
    }

    public Float getPrice(){
        return this.price;
    }

    public boolean isUser(String username, String email, String mobile){
        return (
            this.username.equals(username)
            && this.email.equals(email)
            && this.mobile.equals(mobile)
        );
    }

    public String getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(String showTimeID) {
        this.showTimeID = showTimeID;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }
}   
