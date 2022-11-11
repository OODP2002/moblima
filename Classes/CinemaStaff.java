import java.time.LocalDate;
import java.time.LocalTime;
public class CinemaStaff implements Admin, SysPriceHandler, SysSpecialOccasionHandler, Person{

    private String name;
    private PricingStore pricingStore = PricingStore.getInstance();
    private SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();
    
    
    public CinemaStaff(String name){
        setName(name);
    }

    //Edit pricing rules
    public boolean updateHype(String hype, Float newVal){
        return pricingStore.changeHype(Hype.valueOf(hype.toUpperCase()), newVal);
    }

    public boolean updateCinemaClass(String cinemaClass, Float newVal){
        return pricingStore.changeCinemaClass(CinemaClass.valueOf(cinemaClass.toUpperCase()), newVal);
    }

    public boolean updateDayOfWeek(Integer dayOfWeek, Float newVal){
        return pricingStore.changeDayOfWeek(dayOfWeek, newVal);
    }

    public boolean updateCutOff(LocalTime oldCutOff, LocalTime newCutoff){
        return pricingStore.changeCutOff(oldCutOff, newCutoff);
    }

    public boolean updateFridayRule(LocalTime cutOff, Float newVal){
        return pricingStore.changeFridayRule(cutOff, newVal);
    }

    public boolean updateView(String view, Float newVal){
        return pricingStore.changeView(View.valueOf(view.toUpperCase()), newVal);
    }
 

    //Option 2 - Special Occasion
    public boolean addSpecialOccasion(String date, String name){
        SpecialOccasion specialOccasion =  new SpecialOccasion(date, name);
        return specialOccasionStore.add(specialOccasion);
    }

    public boolean updateSpecialOccasion(String name, LocalDate date){
        return true;
    }

    public boolean removeSpecialOccasion(String date, String name){
        SpecialOccasion specialOccasion =  new SpecialOccasion(date, name);
        return specialOccasionStore.remove(specialOccasion);
    }

    public void printSpecialOccasionList(){
        specialOccasionStore.printAll();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Encapsulation: CinemaStaff responsible for own validation and persistence
    public boolean validate(String password) {
        return password.equals(CredentialStore.getInstance().getPassword(name));
    }
}
