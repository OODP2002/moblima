import java.util.ArrayList;
import java.time.LocalTime;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PricingStore {
    private ArrayList<Pricing> pricings = new ArrayList<Pricing>();


    public void readPricings(){
        //get pricing list file path
        String cwd = System.getProperty("user.dir");
        String path = cwd + ("/src/pricingList.txt");
        
        // Reads a file line by line
        try{

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String header = reader.readLine();
            String line = reader.readLine();
            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException err){
            //System.out.println(err.getMessage());
            System.out.println("Error: Pricing list not found");
        }
    }

    private void createPricingObj(String info){
        String[] infoArr = info.split("|");
        CinemaClassLevels cinemaLevel; 
        View view; 
        AgeGroup ageGroup;
        LocalTime startTime;
        LocalTime endTime;
        int dayOfWeek; 
        boolean isPreferred;
        int price;

        //Cinema Class 
        switch(infoArr[0]){
            case "STANDARD":
                cinemaLevel = CinemaClassLevels.STANDARD;
                break;
            case "GOLD":
                cinemaLevel = CinemaClassLevels.GOLD;
                break;
            case "PLATINUM":
                cinemaLevel = CinemaClassLevels.PLATINUM;
        }

        //View Class
        switch(infoArr[1]){
            case "_2D":
                view = View._2D;
                break;
            case "_3D":
                view = View._3D;
                break;
        }

        // Age Group
        switch(infoArr[2]){
            case "CHILD":
                ageGroup = AgeGroup.CHILD;
                break;
            case "ADULT":
                ageGroup = AgeGroup.ADULT;
                break;
            case "SENIOR":
                ageGroup = AgeGroup.SENIOR;
                break;
        }

        

        //Create and return Price Object 
        Pricing obj = new Pricing(cinemaLevel, view, ageGroup, startTime, endTime, dayOfWeek, isPreferred, price);
    }
}   