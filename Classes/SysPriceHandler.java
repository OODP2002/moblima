import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public interface SysPriceHandler {
    public Scanner sc = new Scanner(System.in);
    static PricingStore pStore =  PricingStore.getInstance();

    default void addPricingRule(){
        Integer ruleClass;
        System.out.println("------Add pricing rule------");
        System.out.println("\n--------------Pricing Rule Classes--------------");
        System.out.println("(1) Age group (CHILD / ADULT / SENIOR)");
        System.out.println("(2) Hype (REGULAR / BLOCKBUSTER)");
        System.out.println("(3) Cinema class (STANDARD / GOLD / PLATINUM)");
        System.out.println("(4) Day of Week (1-7)");
        System.out.println("(5) Friday evening surcharges (hhmm)");
        System.out.println("(6) View (_2D / _3D)");
        System.out.println("(7) Quit");
        System.out.println("------------------------------------------------\n");
        System.out.print("Enter choice: ");
        try{
            ruleClass = sc.nextInt();
        } catch (Exception err) {
            System.out.println("Error: Invalid input, exiting ...");
            sc.nextLine();
            return;
        }
        if (ruleClass < 0 || ruleClass > 7 ){
            System.out.println("Error: Invalid input, exiting...");
            return;
        }
        System.out.print("Enter Type (Refer to pricing rule classes list): ");
        String type = sc.next();
        System.out.print("Enter value of new rule: ");
        
        Float value = sc.nextFloat();
        pStore.addPricingRule(ruleClass, type, value);

    }

    default void updatePricingRule(){
        Integer ruleClass;
        System.out.println("------Update pricing rule------");
        System.out.println("\n--------------Pricing Rule Classes--------------");
        System.out.println("(1) Age group (CHILD / ADULT / SENIOR)");
        System.out.println("(2) Hype (REGULAR / BLOCKBUSTER)");
        System.out.println("(3) Cinema class (STANDARD / GOLD / PLATINUM)");
        System.out.println("(4) Day of Week (1-7)");
        System.out.println("(5) Friday evening surcharges (hhmm)");
        System.out.println("(6) View (_2D / _3D)");
        System.out.println("(7) Quit");
        System.out.println("------------------------------------------------\n");
        System.out.print("Select a pricing rule: ");
        try{
            ruleClass = sc.nextInt();
        } catch (Exception err) {
            System.out.println("Error: Invalid input, exiting ...");
            sc.nextLine();
            return;
        }
        if (ruleClass < 0 || ruleClass > 7 ){
            System.out.println("Error: Invalid input, exiting...");
            return;
        }
        System.out.print("Enter Type (Refer to pricing rule classes list): ");
        String type = sc.next();
        System.out.print("Enter new value for existing rule: ");
        Float value = sc.nextFloat();
        pStore.updatePricingRule(ruleClass, type, value);

    }

    default void removePricingRule() {
        System.out.println("------Remove pricing rule------");
        System.out.println("\n------------Pricing Rule Classes------------");
        System.out.println("(1) Age group (CHILD / ADULT / SENIOR)");
        System.out.println("(2) Hype (REGULAR / BLOCKBUSTER)");
        System.out.println("(3) Cinema class (STANDARD / GOLD / PLATINUM)");
        System.out.println("(4) Day of Week (1-7)");
        System.out.println("(5) Friday evening surcharges (hhmm)");
        System.out.println("(6) View (_2D / _3D)");
        System.out.println("(7) Quit");
        System.out.println("--------------------------------------------\n");
        System.out.print("Enter choice: ");
        Integer ruleClass = sc.nextInt();
        System.out.print("Enter Type (Refer to pricing rule classes list): ");
        String type = sc.next();
        pStore.removePricingRule(ruleClass, type);

    }

    default void printAllPricingRules() {
        pStore.printAllPricings();
    }
}
