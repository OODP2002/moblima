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
            this.date == otherSpecialOccasion.date
            && this.name == otherSpecialOccasion.name
        );
    }
}
