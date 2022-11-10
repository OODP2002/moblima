import java.time.LocalDate;

public class SpecialOccasion {
    private LocalDate date;
    private String name;

    public SpecialOccasion(String cDate, String cName){
        
        String[] dateArr = cDate.split("-");
        int year = LocalDate.now().getYear();
        this.date = LocalDate.of(year, Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0])); 
        this.name = cName;
    }

    public boolean isSameOccasion(SpecialOccasion otherSpecialOccasion){
        return (
            this.date.equals(otherSpecialOccasion.date)
            && this.name.equals(otherSpecialOccasion.name)
        );
    }

    public boolean isSpecialOccasion(LocalDate date){
        return this.date.equals(date) || this.date.equals(date.plusDays(1)); //checks if its a special occasion or eve of a special occasion
    } 
}
