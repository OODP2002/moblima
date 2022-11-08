import java.util.ArrayList;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpecialOccasionStore {
    private ArrayList<SpecialOccasion> specialOccasions = new ArrayList<SpecialOccasion>(); 

    public void readSpecialOccasionFile(){
        //get special occasion list file path
        String cwd = System.getProperty("user.dir");
        String path = cwd + ("/src/specialOccasion.txt");

        try{

            BufferedReader reader = new BufferedReader(new FileReader(path));
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

    private SpecialOccasion createSpecialOccasionObj(String info){
        String[] infoArr =  info.split("\\|");
        String name = infoArr[0];

        String[] dateArr = infoArr[1].split("-");
        int year = LocalDate.now().getYear();
        LocalDate date = LocalDate.of(year, Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0])); 
        System.out.println(info);
        return new SpecialOccasion(date, name);
    }
}
