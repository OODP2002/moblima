/**
 * A Ticket object
 * Every Movie-Goer will have many tickets
 * @author Marc
 * @version 1.0.0 Nov 12, 2022
 */
public class Ticket {
    /**
     * Private attributes which make up the Ticket object
     */
    private String transactionID;
    private double price;
    private String username;
    private String email;
    private String mobile;
    private String seatID;
    private AgeGroup ageGroup;
    /**
     * Default constructor for the Ticket
     * @param transactionID - Used as unique key identifier
     */
    Ticket(String transactionID){
        this.transactionID = transactionID;
    }
    /**
     * Getters for the attributes
     */
    public String getTransactionID() {
        return transactionID;
    }
    public double getPrice() {
        return price;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getMobile() {
        return mobile;
    }
    public String getSeatID() {
        return seatID;
    }
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }
    /**
     * Setters for the attributes
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }
}
