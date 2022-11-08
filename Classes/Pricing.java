import java.time.LocalTime;

public class Pricing {
    private CinemaClass cinemaLevel; //add CinemaClass constructor 
    private View view; //add View Constructor
    private AgeGroup ageGroup;
    private LocalTime startTime;
    private LocalTime endTime;
    private int dayOfWeek; // 1-indexed
    private boolean isPreferred;
    private float price;

    public Pricing(CinemaClass cCinemaLevel, View cView, AgeGroup cAgeGroup, LocalTime cStartTime, LocalTime cEndTime, int cDayofWeek, boolean cIsPreferred, float cPrice){
        this.cinemaLevel = cCinemaLevel;
        this.view = cView; //add View Constructor
        this.ageGroup = cAgeGroup;
        this.startTime = cStartTime;
        this.endTime = cEndTime;
        this.dayOfWeek = cDayofWeek; // 1-indexed
        this.isPreferred = cIsPreferred;
        this.price = cPrice;
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
                temp = "_2D";
                break;
            case _3D:
                temp = "_3D";
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

