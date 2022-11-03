import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create vendor
        Vendor vendor = new Vendor();
//        System.out.println(vendor.getVendorName());

        // Default cineplexes: CCK, Jem, Jurong Point
        vendor.addCineplex("CCK");
        vendor.addCineplex("Jem");
        vendor.addCineplex("Jurong point");

        for (Cineplex cineplex: vendor.cineplexes) {
            System.out.println("Cinema: " + cineplex.getCineplexName());
            for (Cinema cinema: cineplex.cinemas) {
                System.out.println("cinema num: " + cinema.getCinemaID());
                System.out.println("cinema class: " + cinema.getCinemaClass());
            }
        }
    }
}