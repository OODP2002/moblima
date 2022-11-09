import java.util.ArrayList;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpecialOccasionStore {
    //Attributes
    private ArrayList<SpecialOccasion> specialOccasions = new ArrayList<SpecialOccasion>(); 
    private String path = System.getProperty("user.dir") + ("/src/specialOccasions.txt");
    private static SpecialOccasionStore instance = new SpecialOccasionStore();

    //Contstuctor
    private SpecialOccasionStore(){
        //reading special occasion list file path
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                specialOccasions.add(createSpecialOccasionObj(line)); //Add pricing object to special occasion array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            //System.out.println(err.getStackTrace());
            System.out.println("Error: Special Occasion list not found");
        }

    }

    //Operations
    public static SpecialOccasionStore getInstance(){
        return instance;
    }

    //Creates a new special occasion object based on information retreived from one line of the txt file
    private SpecialOccasion createSpecialOccasionObj(String info){
        String[] infoArr =  info.split("\\|");
        String name = infoArr[0];

        String[] dateArr = infoArr[1].split("-");
        int year = LocalDate.now().getYear();
        LocalDate date = LocalDate.of(year, Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0])); 
        return new SpecialOccasion(date, name);
    }

    // Add new special occasion
    public void addSpecialOccasion(SpecialOccasion newSpecialOccasion){
        this.specialOccasions.add(newSpecialOccasion);
        return;
    }

    // Remove a special occasion 
    public void removeSpecialOccasion(SpecialOccasion targetSpecialOccasion){
        this.specialOccasions.remove(targetSpecialOccasion);
    }

    // Overwrite old specialOccasionsList with a new set of Special Occasions
    public void writeToSpecialOccasionFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine(); //Header row
            
            FileWriter writer = new FileWriter(path);
            writer.write(header);
            for (int i = 0; i < this.specialOccasions.size(); i++){
                writer.write("\n" + this.specialOccasions.get(i).toString());
            }
            writer.close();
            reader.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
