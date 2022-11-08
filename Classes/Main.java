import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create vendor
        Vendor vendor = new Vendor();
        vendor.addCineplex();
        System.out.println(vendor.getCineplexHashMap().get("00").getCineplexName());
    }
}