import java.util.ArrayList;
import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
            String header = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                pricings.add(createPricingObj(line)); //Add pricing object to pricings array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            //System.out.println(err.getStackTrace());
            System.out.println("Error: Pricing list not found");
        }
    }

    private Pricing createPricingObj(String info){
        String[] infoArr = info.split("\\|");
        CinemaClassLevels cinemaLevel; 
        View view; 
        AgeGroup ageGroup;
        LocalTime startTime = LocalTime.of(0,0);
        LocalTime endTime = LocalTime.of(23,59);
        int dayOfWeek = 0; 
        boolean isPreferred;
        float price = 0;

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
                break;
            default:
                cinemaLevel = CinemaClassLevels.STANDARD;
        }

        //View Class
        switch(infoArr[1]){
            case "_2D":
                view = View._2D;
                break;
            case "_3D":
                view = View._3D;
                break;
            default:
                view = View._2D;
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
            default:
                ageGroup = AgeGroup.ADULT;
        }
        
        //Time 
        String[] startTimeArr = infoArr[3].split(":");
        try{
            startTime = LocalTime.of(Integer.parseInt(startTimeArr[0]), Integer.parseInt(startTimeArr[1]));
        } catch (NumberFormatException err){
           err.printStackTrace();
        }

        String[] endTimeArr = infoArr[4].split(":");
        try{
            endTime = LocalTime.of(Integer.parseInt(endTimeArr[0]), Integer.parseInt(endTimeArr[1]));
        } catch (NumberFormatException err){
           err.printStackTrace();
        }

        //Day of Week
        try{  
            dayOfWeek = Integer.parseInt(infoArr[5]);
        } catch(NumberFormatException err) {
           err.printStackTrace();
        }

        // Is Preferred 
        switch(infoArr[6]){
            case "true":
                isPreferred = true;
                break;
            case "false":
                isPreferred = false;
                break;
            default:
                isPreferred = false;
        }

        //price 
        try{
            price = Float.parseFloat(infoArr[7]);
        } catch(NumberFormatException err) {
           err.printStackTrace();
        }

        //Create and return Price Object 
        return new Pricing(cinemaLevel, view, ageGroup, startTime, endTime, dayOfWeek, isPreferred, price);
    }

    //Edit pricing 
    public void editPricing(Pricing newPricing){
        for (int i = 0; i < this.pricings.size(); i++){
            if (pricings.get(i).comparePricing(newPricing)) {
                pricings.get(i).setPrice(newPricing.getPrice());
            } else {
                pricings.add(newPricing);
            }
        } 
    }

    //Overwrite the old priceList with a new set of Pricings
    //To do: function should only be trigger
    //To do: edit file to only change the affected line as the edit is being made (helps to prevent information loss if app crashes before this function is called.)
    public void writeAllPricings(){
        String cwd = System.getProperty("user.dir");
        String path = cwd + ("/src/pricingList.txt");

        try{
            FileWriter writer = new FileWriter(path, true);
            for (int i = 0; i < this.pricings.size(); i++){
                writer.write("\n" + this.pricings.get(i).toString());
            }
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}   