import java.io.*;
import java.util.ArrayList;

/** @author mingyang
   Purpose of this clas:
   1. Read from .txt into raw ArrayList<String[]> using readFile(String fileSource) --> taken care of automatically
   2. Write from raw ArrayList<String[]> into .txt file using writeFile(String fileSource) --> taken care of automatically

   Notes:
   ArrayList should only be read and written once by respective store class.
   Updates to stores should update store class only and not ArrayList
   Header file will be automatically taken care of by TxtReaderWriter
 */
public class TxtReaderWriter {
    private ArrayList<String[]> rawStringFromFile = new ArrayList<>();
    private String header;
    private final String FILE_SOURCE;

    public TxtReaderWriter(String fileSource) {
        this.FILE_SOURCE = fileSource;
        readFile();
    }

    public ArrayList<String[]> getRawStringFromFile() {
        return rawStringFromFile;
    }

    public void setRawStringFromFile(ArrayList<String[]> rawStringFromFile) {
        this.rawStringFromFile = rawStringFromFile;
        writeFile();
    }

    // Call before terminating program
    private void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(header);

            for (String[] line: rawStringFromFile) {
                writer.write("\n" + String.join("|", line));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.header = reader.readLine();    // Header

            String line = reader.readLine();
            while (line != null) {
                rawStringFromFile.add(line.split("\\|"));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
