// Done by Mingyang
import java.util.ArrayList;

public class Vendor {
    private String vendorName;
    public ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();

    // Default vendor name: Cathay
    public Vendor() {
        this.vendorName = "Cathay Cineplexes";
    }

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void addCineplex(String cineplexName) {
        cineplexes.add(new Cineplex(cineplexName, cineplexes.size() + 1));
    }
}

// Notes: add changeVendorName() method in the future when there is time