import java.time.LocalDate;
public class CinemaStaff implements Admin, SysPriceHandler, SysSpecialOccasionHandler{

    private String name;
    private PricingStore pricingStore = PricingStore.getInstance();
    private SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();
    
    
    public CinemaStaff(String name){
        this.name = name;
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
}
