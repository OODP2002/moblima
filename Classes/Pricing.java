import java.time.LocalTime;

public class Pricing {
    private CinemaClassLevels cinemaLevel; //add CinemaClass constructor 
    private View view; //add View Constructor
    private AgeGroup ageGroup;
    private LocalTime startTime;
    private LocalTime endTime;
    private int dayOfWeek; // 1-indexed
    private boolean isPreferred;
    private int price;

    public Pricing(CinemaClassLevels cCinemaLevel, View cView, AgeGroup cAgeGroup, LocalTime cStartTime, LocalTime cEndTime, int cDayofWeek, boolean cIsPreferred, int cPrice){
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
    public int getPrice(){
        return price;
    }

    //sets pricing for ticket type
    public void setPrice(int newPrice){
        this.price = newPrice;
    }

    //checks if Pricing is the same pricing instance 
    public boolean isPricing(CinemaClassLevels testCinemaLevel, View testView, AgeGroup testAgeGroup, LocalTime testTime, int testDayOfWeek, boolean testIsPreferred){
        return (
            testCinemaLevel == this.cinemaLevel 
            && testView == this.view 
            && testAgeGroup == this.ageGroup
            && (testTime.isAfter(this.startTime) && testTime.isBefore(this.endTime))
            && testDayOfWeek == this.dayOfWeek
            && testIsPreferred == this.isPreferred
        );
    }
}

