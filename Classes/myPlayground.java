import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myPlayground {
    public static void main(String[] args) throws FileNotFoundException {
//        File f = new File("Classes/src/cineplexes.txt");
//        Scanner sc = new Scanner(f);
//
//        System.out.println(sc.next().split("\\|")[0]);

//        System.out.println(vendor.getVendorName());

        // Default cineplexes: CCK, Jem, Jurong Point
        Cinema cinema = new Cinema(3, new CinemaClass(CinemaClassLevels.STANDARD), new Layout(10, 8, 3));
        Layout layout = cinema.getLayout();
        layout.printLayout();
    }
}
