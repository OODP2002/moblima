import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class PricingStore {
    //Attributes
    private String pricingHeader;
    private ArrayList<Pricing> pricings = new ArrayList<Pricing>();
    private String path = System.getProperty("user.dir") + ("/src/pricings.txt");
    private static PricingStore instance = new PricingStore();

    //Constructor
    private PricingStore(){
        // Reads a file line by line
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            this.pricingHeader = reader.readLine(); //Header row
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

    //Operations
    public static PricingStore getInstance(){
        return instance;
    }
    
    private Pricing createPricingObj(String info){
        String[] infoArr = info.split("\\|");
        return new Pricing(infoArr[0], infoArr[1], infoArr[2], infoArr[3], infoArr[4], infoArr[5], infoArr[6], infoArr[7],  infoArr[8]);
    }

    //New pricing 
    public void newRule(Pricing newPricing){
        //Edit existing pricing object or add new pricing object into the array list
        for (int i = 0; i < this.pricings.size(); i++){
            if (pricings.get(i).comparePricing(newPricing)) {
                pricings.get(i).setPrice(newPricing.getPrice());
            } else {
                pricings.add(newPricing);
            }
        } 
        pricings.add(newPricing);
    }

    public boolean remove(Integer pricingID){
        for (int i = 0; i < pricings.size(); i++){
            if (pricings.get(i).getPricingID().equals(pricingID)){
                pricings.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Integer numPricings(){
        return this.pricings.size();
    }

    public void printAll(){
        System.out.println();
        for (int i = 0; i < pricings.size(); i++){
            System.out.println(pricings.get(i).toString());
        }
    }

    //Overwrite the old priceList with a new set of Pricings
    //To do: function should only be trigger
    //To do: edit file to only change the affected line as the edit is being made (helps to prevent information loss if app crashes before this function is called.)
    public void writeToPricingsFile(){
        try{        
            FileWriter writer = new FileWriter(this.path);
            writer.write(this.pricingHeader);
            for (int i = 0; i < this.pricings.size(); i++){
                writer.write("\n" + this.pricings.get(i).toString());
            }
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}   