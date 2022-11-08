// Done by Mingyang
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Vendor {
    private String vendorName;
    private HashMap<String, Cineplex> cineplexHashMap = new HashMap<>();

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

    public void addCineplex() {
        CineplexStore cineplexStore = new CineplexStore();
        try {
            cineplexStore.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.cineplexHashMap = cineplexStore.getCineplexMap();
    }

    public HashMap<String, Cineplex> getCineplexHashMap() {
        return cineplexHashMap;
    }
}

// Notes: add changeVendorName() method in the future when there is times