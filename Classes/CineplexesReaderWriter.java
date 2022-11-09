// Done by Mingyang

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CineplexesReaderWriter {
    private BufferedReader reader;
    private BufferedWriter writer;

    public CineplexesReaderWriter() {
        try {
            String FILE_SOURCE = "Classes/src/cineplexes.txt";
            this.reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error: cineplexes.txt not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

    // writeFile() should be called only from the admin module, and it will write directly into the txt
    public void writeFile() throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> out = new ArrayList<>();
        System.out.println("Welcome to add cineplex module");

        System.out.println("Enter Cineplex ID: ");
        out.add(sc.next("[0-9][0-9]"));

        System.out.println("Enter Cineplex name: ");
        out.add(sc.nextLine().trim().replace('|', ' '));

        System.out.println("Enter Cinema no.: ");
        out.add(sc.next("[0-9][0-9]"));

        System.out.println("Enter cinema class (STANDARD/ GOLD/ PLATINUM: " );
        String cinemaClass = sc.next().toUpperCase();
        // Check if cinemaClass is valid
        if (!CinemaClass.isValid(cinemaClass)) {
            System.out.println("Invalid cinema class input, default of STANDARD is chosen");
            cinemaClass = "STANDARD";
        }
        out.add(cinemaClass);

        System.out.println("Enter number of rows: ");
        out.add(String.valueOf(sc.nextInt()));

        System.out.println("Enter number of columns: ");
        out.add(String.valueOf(sc.nextInt()));

        System.out.println("Enter aisle number: ");
        out.add(String.valueOf(sc.nextInt()));

        System.out.println("Enter main stairway number: ");
        out.add(String.valueOf(sc.nextInt()));

        System.out.println(out);        // for debugging, remove
        writer.write(String.join("|", out));
    }
}
