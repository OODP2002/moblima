import java.time.LocalDate;
public class CinemaStaff implements Admin, SysPriceHandler, SysSpecialOccasionHandler{

    private String name;
    private PricingStore pricingStore = PricingStore.getInstance();
    private SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();
    
    
    public CinemaStaff(String name){
        this.name = name;
    }

    //Option 1 - Pricings
    public void editPricing(Pricing newPricing){    
        pricingStore.newRule(newPricing);
        System.out.println("Pricing rule added.");
        return;
    }
    
    public void removePricing(Integer pricingID){
        if(pricingStore.remove(pricingID)){
            System.out.println("Pricing rule removed.");
        }
        return;
    }

    public void printPricingList(){
        pricingStore.printAll();
    }

    //Option 2 - Special Occasion
    public boolean addSpecialOccasion(String date, String name){
        SpecialOccasion specialOccasion =  new SpecialOccasion(date, name);
        return specialOccasionStore.add(specialOccasion);
    }

    public boolean updateSpecialOccasion(String name, LocalDate date){

    }

    public boolean removeSpecialOccasion(String date, String name){
        SpecialOccasion specialOccasion =  new SpecialOccasion(date, name);
        return specialOccasionStore.remove(specialOccasion);
    }

    public void printSpecialOccasionList(){
        specialOccasionStore.printAll();
    }
    }
}
