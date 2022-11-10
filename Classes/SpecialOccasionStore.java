import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpecialOccasionStore {
    //Attributes
    private String specialOccasionHeader;
    private ArrayList<SpecialOccasion> specialOccasions = new ArrayList<SpecialOccasion>(); 
    private String path = System.getProperty("user.dir") + ("/src/specialOccasions.txt");
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
        System.out.println(this.specialOccasionHeader);
        for (int i = 0; i < this.specialOccasions.size(); i++){
            System.out.println(this.specialOccasions.get(i).toString());
        }
    }

    // Overwrite old specialOccasionsList with a new set of Special Occasions
    public void writeToSpecialOccasionFile(){
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(this.specialOccasionHeader);
            for (int i = 0; i < this.specialOccasions.size(); i++){
                writer.write("\n" + this.specialOccasions.get(i).toString());
            }
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
