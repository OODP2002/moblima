// Done by Mingyang
import java.util.ArrayList;

public class Vendor {
    private String vendorName;

    // Default vendor name: Cathay
    public Vendor() {
        this.vendorName = "Cathay Cineplexes";
        ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    }

    public String getVendorName() {
        return vendorName;
    }
}

// Notes: add changeVendorName() method in the future when there is times