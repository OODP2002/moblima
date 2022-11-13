// Done by Mingyang
import java.io.IOException;
import java.util.HashMap;

public class Vendor {
    private String vendorName;
    private HashMap<String, Cineplex> cineplexHashMap = new HashMap<>();

    // Default vendor name: Cathay
    public Vendor() {
        this.vendorName = "Cathay Cineplexes";
        addCineplex();
    }

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    private void addCineplex() {
        CineplexStore cineplexStore = CineplexStore.getInstance();
        this.cineplexHashMap = cineplexStore.getCineplexHashMap();
    }
}

// Notes: add changeVendorName() method in the future when there is time