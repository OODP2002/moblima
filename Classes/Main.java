import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
         // Create vendor
        Vendor vendor = new Vendor();

        System.out.println("Starting app...");
        System.out.println("Welcome to " + vendor.getVendorName() + "Movie Booking System");
        int loginChoice = 0;

        while(loginChoice != 3){
            System.out.println("-----Login Panel-----");
            System.out.println("(1) Admin");
            System.out.println("(2) Customer (Sign in required)");
            System.out.println("(3) Guest");
            System.out.println("(4) Quit ");
            System.out.println("---------------------");
            System.out.print("Choice: ");

            loginChoice = sc.nextInt();
            sc.nextLine();

            switch (loginChoice) {
                case 1 -> AdminModule.getInstance();
                case 2 -> CustomerModule.getInstance();
                case 3 -> GuestModule.getInstance();
                case 4 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid Choice.");
            }
        }
    }
}
