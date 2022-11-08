import java.io.IOException;

public class CinemaStoreDemo {
    public static void main(String[] args) {
//        System.out.println("demo starts");
//        CineplexStore cineplexStore = new CineplexStore();
//        try {
//            cineplexStore.readFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(cineplexStore.getCineplexMap().get("00").getCineplexName());
        Vendor vendor = new Vendor();
        vendor.addCineplex();

    }
}
