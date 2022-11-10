import java.time.LocalTime;

public class Pricing {
    private Integer pricingID;
    private CinemaClass cinemaLevel; //add CinemaClass constructor 
    private View view; //add View Constructor
    private AgeGroup ageGroup;
    private LocalTime startTime;
    private LocalTime endTime;
    private int dayOfWeek; // 1-indexed
    private boolean isPreferred;
    private float price;

    public Pricing(String cPricingID, String cCinemaLevel, String cView, String cAgeGroup, String cStartTime, String cEndTime, String cDayofWeek, String cIsPreferred, String cPrice){
        //PricingID
        this.pricingID = Integer.parseInt(cPricingID);
        
        //Cinema Class 
        switch(cCinemaLevel){
            case "STANDARD":
                cinemaLevel = CinemaClass.STANDARD;
                break;
            case "GOLD":
                cinemaLevel = CinemaClass.GOLD;
                break;
            case "PLATINUM":
                cinemaLevel = CinemaClass.PLATINUM;
                break;
            default:
                cinemaLevel = CinemaClass.STANDARD;
        }

        //View Class
        switch(cView){
            case "2D":
                this.view = View._2D;
                break;
            case "3D":
                this.view = View._3D;
                break;
            default:
                this.view = View._2D;
        }

        // Age Group
        switch(cAgeGroup){
            case "CHILD":
                this.ageGroup = AgeGroup.CHILD;
                break;
            case "ADULT":
                this.ageGroup = AgeGroup.ADULT;
                break;
            case "SENIOR":
                this.ageGroup = AgeGroup.SENIOR;
                break;
            default:
                this.ageGroup = AgeGroup.ADULT;
        }
        
        //Time 
        String[] startTimeArr = cStartTime.split(":");
        try{
            this.startTime = LocalTime.of(Integer.parseInt(startTimeArr[0]), Integer.parseInt(startTimeArr[1]));
        } catch (NumberFormatException err){
           err.printStackTrace();
        }

        String[] endTimeArr = cEndTime.split(":");
        try{
            this.endTime = LocalTime.of(Integer.parseInt(endTimeArr[0]), Integer.parseInt(endTimeArr[1]));
        } catch (NumberFormatException err){
           err.printStackTrace();
        }

        //Day of Week
        try{  
            this.dayOfWeek = Integer.parseInt(cDayofWeek);
        } catch(NumberFormatException err) {
           err.printStackTrace();
        }

        // Is Preferred 
        switch(cIsPreferred){
            case "true":
                this.isPreferred = true;
                break;
            case "false":
                this.isPreferred = false;
                break;
            default:
                this.isPreferred = false;
        }

        //price 
        try{
            this.price = Float.parseFloat(cPrice);
        } catch(NumberFormatException err) {
           err.printStackTrace();
        }
    }

    //returns pricingID
    public Integer getPricingID(){
        return this.pricingID;
    }

    //returns price of ticket
    public float getPrice(){
        return price;
    }

    //sets pricing for ticket type
    public void setPrice(float newPrice){
        this.price = newPrice;
    }

    //query for pricing when buying ticket 
    public boolean isPricing(CinemaClass testCinemaLevel, View testView, AgeGroup testAgeGroup, LocalTime testTime, int testDayOfWeek, boolean testIsPreferred){
        return (
            testCinemaLevel == this.cinemaLevel 
            && testView == this.view 
            && testAgeGroup == this.ageGroup
            && (testTime.isAfter(this.startTime) && testTime.isBefore(this.endTime))
            && testDayOfWeek == this.dayOfWeek
            && testIsPreferred == this.isPreferred
        );
    }

    //check if this pricing object has the same conditions as another pricing object
    public boolean comparePricing(Pricing otherPricing){
       return (
            this.cinemaLevel == otherPricing.cinemaLevel 
            && this.view == otherPricing.view 
            && this.ageGroup == otherPricing.ageGroup 
            && this.dayOfWeek == otherPricing.dayOfWeek 
            && this.startTime == otherPricing.startTime 
            && this.endTime == otherPricing.endTime 
            && this.isPreferred == otherPricing.isPreferred 
        );
    }

    public String toString(){
        String info = ""; 
        String temp = "";

        //priceID
        info += this.pricingID.toString() + "|";

        //Cinema Level 
        switch(this.cinemaLevel){
            case STANDARD:
                temp = "STANDARD";
                break;
            case GOLD:
                temp = "GOLD";
                break;
            case PLATINUM:
                temp = "PLATINUM";
                break;
        }
        info += temp + "|";

        //View 
        switch(this.view){
            case _2D:
                temp = "2D";
                break;
            case _3D:
                temp = "3D";
                break;
        }
        info += temp + "|"; 

        //Age group 
        switch(this.ageGroup){
            case CHILD:
                temp = "CHILD";
                break;
            case ADULT:
                temp = "ADULT";
                break;
            case SENIOR:
                temp = "SENIOR";
                break;
        }
        info += temp + "|"; 

        //Start Time
        temp = String.valueOf(this.startTime.getHour()) + ":" + String.valueOf(this.startTime.getMinute());
        info += temp + "|";
        
        //End Time
        temp = String.valueOf(this.endTime.getHour()) + ":" + String.valueOf(this.endTime.getMinute());
        info += temp + "|";

        //Day of Week
        temp = String.valueOf(this.dayOfWeek);
        info += temp + "|";

        //Is Preferred 
        temp = this.isPreferred ? "true" : "false";
        info += temp + "|";

        //Price
        temp = String.valueOf(this.price);
        info += temp + "|";

        return info;
    }
}