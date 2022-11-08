// Done by Mingyang

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CineplexesReaderWriter {
    private ArrayList<String[]> cineplexRawStore = new ArrayList<>();
    private final String FILE_SOURCE = "Classes/src/cineplexes.txt";
    private String HEADER;
    private static CineplexesReaderWriter single_instance = null;

    // Implementation: during inialization, it immediately reads file into ArrayList
    private CineplexesReaderWriter() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.HEADER = reader.readLine();

            String line = reader.readLine();
            while (line != null) {
                cineplexRawStore.add(line.split("\\|"));     // Add raw String from .txt
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error: cineplexes.txt not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Singleton
    public static CineplexesReaderWriter getInstance() {
        if (single_instance == null)
            single_instance = new CineplexesReaderWriter();

        return single_instance;
    }

    // Write from ArrayList into .txt
    public void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(HEADER);
            for (String[] line : cineplexRawStore) {
                writer.write("\n" + String.join("|", line));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String[]> getCineplexRawStore() {
        return cineplexRawStore;
    }

    // addCineplexData() should be called only from the admin module, and it will append the ArrayList
    public void addCineplexData() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> out = new ArrayList<>();
        System.out.println("Welcome to add cineplex module");

        System.out.println("Enter Cineplex ID: ");
        out.add(sc.next("[0-9][0-9]"));
        sc.nextLine();

        System.out.println("Enter Cineplex name: ");
        out.add(sc.nextLine().trim().replace('|', ' '));

        System.out.println("Enter Cinema no.: ");
        out.add(sc.next("[0-9][0-9]"));
        sc.nextLine();

        System.out.println("Enter cinema class (STANDARD/ GOLD/ PLATINUM): " );
        String cinemaClass = sc.nextLine().trim().toUpperCase();
        // Check if cinemaClass is valid
        if (!CinemaClass.isValid(cinemaClass)) {
            System.out.println("Invalid cinema class input, default of STANDARD is chosen");
            cinemaClass = "STANDARD";
        }
        out.add(cinemaClass);

        System.out.println("Enter number of rows: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Enter number of columns: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Enter aisle number: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println("Enter main stairway number: ");
        out.add(String.valueOf(sc.nextInt()));
        sc.nextLine();

        System.out.println(String.join("|", out));        // for debugging, remove
        String[] outFormatted = new String[out.size()];
        outFormatted = out.toArray(outFormatted);
        cineplexRawStore.add(outFormatted);
    }
}
