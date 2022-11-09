public class Ticket {
    private String transactionID;
    private String username;
    private String email;
    private Double mobile;
    private String seatID;
    private AgeGroup ageGroup;

    Ticket(String transactionID, String username,String email,Double mobile,String seatID, AgeGroup ageGroup){
        this.transactionID = transactionID;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.seatID = seatID;
        this.ageGroup = ageGroup;
    }

    public String getTID(){
        return this.transactionID;
    }

    public boolean isUser(String username, String email, Double mobile){
        return (
            this.username.equals(username)
            && this.email.equals(email)
            && this.mobile == mobile
        );
    }

    public String toString(){
        String mobileStr = Double.toString(this.mobile);
        String ageGroupStr;
        switch(ageGroup){
            case CHILD:
                ageGroupStr = "CHILD";
                break;
            case ADULT:
                ageGroupStr = "ADULT";
                break;
            case SENIOR:
                ageGroupStr = "SENIOR";
                break;
            default:
                ageGroupStr = "ADULT";
        }
        return this.transactionID + "|" + this.username + "|" + this.email + "|" + mobileStr + "|" + this.seatID  + "|" + ageGroupStr + "|";
    }
}   
