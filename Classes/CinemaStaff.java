import java.time.LocalDate;
public class CinemaStaff implements Admin, SysPriceHandler, SysSpecialOccasionHandler, Person{

    private String name;
    private PricingStore pricingStore = PricingStore.getInstance();
    private SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();
    
    
    public CinemaStaff(String name){
        setName(name);
    }

    public void editPricing(){
        
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
