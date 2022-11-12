import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class SpecialOccasionStore {
    //Attributes
    private String specialOccasionHeader;
    private ArrayList<SpecialOccasion> specialOccasions = new ArrayList<SpecialOccasion>(); 
    private String path = ("Classes/src/specialOccasions.txt");
    private static SpecialOccasionStore instance = new SpecialOccasionStore();

    //Contstuctor
    private SpecialOccasionStore(){
        //reading special occasion list file path
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            this.specialOccasionHeader = reader.readLine(); //Header row
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
        return new SpecialOccasion(infoArr[1], infoArr[0]);
    }

    // Add new special occasion
    public boolean add(SpecialOccasion specialOccasion){
        for (int i = 0; i < specialOccasions.size(); i++){
            if(specialOccasions.get(i).isSameOccasion(specialOccasion)){
                System.out.println("Error: Special occasion already declared");
                return false;
            }
        } 
        this.specialOccasions.add(specialOccasion);
        return true;     
    }


    // Remove a special occasion 
    public boolean remove(SpecialOccasion targetSpecialOccasion){
        for (int i = 0; i < specialOccasions.size(); i++){
            if(specialOccasions.get(i).isSameOccasion(targetSpecialOccasion)){
                this.specialOccasions.remove(targetSpecialOccasion);      
                return true;
            }
        } 
        System.out.println("Error: Special occasion not found");
        return false;
    }

    //List all special occasions
    public void printAll(){
        System.out.println("\n-------All Special Occassions-------");
        for (SpecialOccasion occasion : specialOccasions) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MM");

            System.out.println("Public holiday: " + occasion.getName().toUpperCase());
            System.out.println("Date (DD - MM): " + occasion.getDate().format(formatter));
            System.out.println("\n");
        }
    }

    //query if date provided is a special occasion or eve of special occasion 
    public boolean isSpecialOccasion(LocalDate date){
        for (int i = 0; i < this.specialOccasions.size(); i++){
            if(this.specialOccasions.get(i).isSpecialOccasion(date)) return true;
        }
        return false;
    }

    // Overwrite old specialOccasionsList with a new set of Special Occasions
    public void writeToSpecialOccasionFile(){
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(this.specialOccasionHeader);

            for (SpecialOccasion occasion: specialOccasions) {
                ArrayList<String> line = new ArrayList<>();
                line.add(occasion.getName());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
                line.add(occasion.getDate().format(formatter));
                writer.write("\n" + String.join("|", line));
            }
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
