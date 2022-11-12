import java.util.Scanner;

public class SysListingOptionsModule {
    Scanner sc = new Scanner(System.in);

    public void run() {
        SettingStore settingStore = SettingStore.getInstance();
        String setting = "list-by";

        System.out.println("Welcome to System Listing options Module");
        System.out.println("Select an option below (1-4):");
        System.out.println("1 - Allow both listing options");
        System.out.println("2 - List by sales only");
        System.out.println("3 - List by reviews only");
        System.out.println("4 - Quit");
        System.out.println("-----------------------------");
        System.out.print("Select an option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> {
                settingStore.setSetting(setting, "NIL");
                System.out.println("Setting success! New setting: NIL");
            }
            case 2 -> {
                settingStore.setSetting(setting, "SALES");
                System.out.println("Setting success! New setting: SALES");
            }
            case 3 -> {
                settingStore.setSetting(setting, "AVG_RTG");
                System.out.println("Setting success! New setting: AVERAGE RATING");
            }
            case 4 -> System.out.println("Exiting system listing options Module");
            default -> {
                System.out.println("Invalid option, default setting of NIL chosen");
                settingStore.setSetting(setting, "NIL");
            }
        }
    }
}
