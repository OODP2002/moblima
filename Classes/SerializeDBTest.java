import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class SerializeDBTest {
    public static List readSerializedObject(String filename) {
        List cinemaDetails = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            cinemaDetails = (ArrayList) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // Print out the size
        System.out.println("Details size: " + cinemaDetails.size());
        return cinemaDetails;
    }

    public static void writeSerializeObject(String filename, List list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            System.out.println("Object persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List list ;
        try {
            // Read from serialized file the list of Cineplexes
            list = (ArrayList) SerializeDBTest.readSerializedObject("Classes/src/cineplexes.dat");
            for (int i = 0; i < list.size(); i++) {
                Cineplex c = (Cineplex) list.get(i);
                System.out.println("name is " + c.getCineplexName());
                System.out.println("cineplex id is " + c.getCineplexID());
            }

//            // Write to serialized file - update/ insert/ delete
//            Cineplex c = new Cineplex("CCK", 1);
//            list.add(c);
//            System.out.println(list.get(0));
//            SerializeDBTest.writeSerializeObject("Classes/src/cineplexes.dat", list);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
