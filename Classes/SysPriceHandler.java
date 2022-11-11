import java.util.Scanner;

public interface SysPriceHandler {
    Scanner sc = new Scanner(System.in);
    default void newPricingRule() {
        System.out.print("Enter Cinema Type (PLATINUM / GOLD / STANDARD): ");
        CinemaClass cinemaLevel;
        try {
            cinemaLevel = CinemaClass.valueOf(sc.nextLine().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Invalid cinema class! Default value of STANDARD chosen");
            cinemaLevel = CinemaClass.STANDARD;
        }

        System.out.print("Enter View Type (2D/3D): ");
        View view;
        try {
            view = View.valueOf("_".concat(sc.nextLine().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Invalid view type! Default value of 2D chosen");
            view = View._2D;
        }

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
