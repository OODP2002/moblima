import java.util.Scanner;

interface SysSpecialOccasionHandler {
    Scanner sc = new Scanner(System.in);
    default boolean addSpecialOccasion() {
        SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();

        System.out.print("Enter occasion name: ");
        String name = sc.nextLine();
        System.out.print("Enter date (DD-MM): ");
        String newDate = sc.nextLine();
        SpecialOccasion specialOccasion = new SpecialOccasion(newDate, name);

        return specialOccasionStore.add(specialOccasion);
    }

    default boolean removeSpecialOccasion() {
        SpecialOccasionStore specialOccasionStore = SpecialOccasionStore.getInstance();

        System.out.print("Enter occasion name: ");
        String name = sc.next();
        System.out.print("Enter date (DD-MM): ");
        String date = sc.next();

        return specialOccasionStore.remove(new SpecialOccasion(date, name));
    }

    default void printSpecialOccasions() {
        SpecialOccasionStore.getInstance().printAll();
    }
}
