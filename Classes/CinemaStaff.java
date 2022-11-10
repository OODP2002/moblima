import java.time.LocalDate;
public class CinemaStaff implements Admin, SysPriceHandler, SysSpecialOccasionHandler, Person{

    private String name;
    private PricingStore pricingStore = PricingStore.getInstance();
    private SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();
    
    
    public CinemaStaff(String name){
        setName(name);
    }

    public void editPricing(Pricing newPricing){    
        pricingStore.addPricing(newPricing);
        return;
    }

    public void addSpecialOccasion(String name, LocalDate date){

        SpecialOccasion newSpecialOccasion =  new SpecialOccasion(date, name);
        specialOccasionStore.addSpecialOccasion(newSpecialOccasion);
        return;
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
