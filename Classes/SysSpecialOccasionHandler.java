import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

interface SysSpecialOccasionHandler {
    Scanner sc = new Scanner(System.in);
    default boolean addSpecialOccasion() {
        SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();

        System.out.print("Enter occasion name: ");
        String name = sc.nextLine();
        System.out.print("Enter date (DD-MM): ");
        String newDate = sc.nextLine();

        // Check if date is of valid format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM").withResolverStyle(ResolverStyle.STRICT);
        SpecialOccasion specialOccasion;
        try {
            formatter.parse(newDate);
            specialOccasion = new SpecialOccasion(newDate, name);
        } catch (Exception e) {
            System.out.println("Invalid date format, returning...");
            return false;
        }

        return specialOccasionStore.add(specialOccasion);
    }

    default boolean removeSpecialOccasion() {
        SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();

        System.out.print("Enter occasion name: ");
        String name = sc.nextLine().toLowerCase();
        System.out.print("Enter date (DD-MM): ");
        String date = sc.nextLine();

        return specialOccasionStore.remove(new SpecialOccasion(date, name));
    }

    default void printSpecialOccasions() {
        SpecialOccasionStore.getInstance().printAll();
    }
}
