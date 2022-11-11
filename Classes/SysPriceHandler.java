import java.util.Scanner;

public interface SysPriceHandler {
    Scanner sc = new Scanner(System.in);
    default void newPricingRule() {
        System.out.print("Enter Cinema Type (PLATINUM / GOLD / STANDARD): ");
//        cinemaLevel = sc.next();
        System.out.print("Enter View Type (2D / 3D): ");
//        view = sc.next();
        System.out.print("Enter Age Group (CHILD / ADULT / SENIOR): ");
//        ageGroup = sc.next();
        System.out.print("Enter Start Time (hh:mm): ");
//        startTime = sc.next();
        System.out.print("Enter End Time (hh:mm): ");
//        endTime = sc.next();
        System.out.print("Enter Day of Week (1-7): ");
//        dayofWeek = sc.next();
        System.out.print("Is Preferred Customer? (True / False): ");
//        isPreferred = sc.next();
        System.out.print("Enter Price : $");
//        price = sc.next();

        //Creates a temporary pricingID for Pricing object. Pricing object will take on this pricingID if this rule does not exist within current pricing rules
        //pricingID = PricingStore.getInstance().numPricings().toString();
        //this.cinemaStaff.editPricing(new Pricing(pricingID, cinemaLevel, view, ageGroup, startTime, endTime, dayofWeek, isPreferred, price));
    }

    default void removePricingRule() {
        System.out.print("Enter Pricing ID to be removed: ");
//        pricingID = sc.next();
        //this.cinemaStaff.removePricing(Integer.parseInt(pricingID));
    }

    default void printAllPricingRules() {
        //this.cinemaStaff.printPricingList();
    }
}
