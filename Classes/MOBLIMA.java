import java.util.Scanner;

public class MOBLIMA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         // Create vendor
        Vendor vendor = new Vendor();

        AdminModule adminModule= new AdminModule();
        MovieGoerModule movieGoerModule = new MovieGoerModule();
        GuestModule guestModule = new GuestModule();

        System.out.println("\nStarting app...");
        System.out.println("Welcome to " + vendor.getVendorName() + " Movie Booking System");
        int loginChoice = 0;

        while(loginChoice != 4){
            System.out.println("\n----------Login Panel----------");
            System.out.println("(1) Admin");
            System.out.println("(2) Customer (Sign in required)");
            System.out.println("(3) Guest");
            System.out.println("(4) Quit ");
            System.out.println("-------------------------------");
            System.out.print("\nChoice: ");

            
            try{
                loginChoice = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 4).\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (loginChoice) {
                case 1 -> adminModule.run();
                case 2 -> movieGoerModule.run();
                case 3 -> guestModule.run();
                case 4 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid Choice.");
            }
           
        }
        StoreManager.closeAllStores();
    }
}
