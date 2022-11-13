import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;

public class PricingStore {
    //Attributes
    private String pricingHeader;

    private Float base;
    private Map<AgeGroup, Float> ageGroupChange =  new HashMap<AgeGroup, Float>();
    private Map<Hype, Float> hypeAdd = new HashMap<Hype, Float>();
    private Map<CinemaClass,  Float> cinemaClassAdd = new HashMap<CinemaClass, Float>();
    private Map<Integer, Float> dayOfWeekAdd = new HashMap<Integer, Float>();
    private LocalTime cutOff;
    private Map<LocalTime, Float> fridayRuleAdd = new HashMap<LocalTime, Float>();
    private Map<View, Float> viewAdd = new HashMap<View, Float>();
    
    private String path = ("Classes/src/pricings.txt");
    private static PricingStore instance = new PricingStore();

    //Constructor
    private PricingStore(){
        // Reads a file line by line
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            this.pricingHeader = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                createPricingRule(line); //Add pricing object to pricings array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            err.printStackTrace();
            System.out.println("Error: Pricing list not found");
        }
    }

    //operations
    public static PricingStore getInstance(){
        return instance;
    }

    //For reading pricing rule form txt
    private void createPricingRule(String info){
        String[] infoArr = info.split("\\|");

        switch(infoArr[0]){
            case "base":
                this.base = Float.parseFloat(infoArr[2]);
                break;
                
            case "ageGroup":
                this.ageGroupChange.put(AgeGroup.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));  
                break;

            case "hype":
                this.hypeAdd.put(Hype.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

            case "cinemaClass":
                this.cinemaClassAdd.put(CinemaClass.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

            case "dayOfWeek":
                this.dayOfWeekAdd.put(Integer.parseInt(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

            case "fridayRule":
                //infoArr[1] is in the (hhmm) format
                int hour = Integer.parseInt(infoArr[1].substring(0,2));
                int minute = Integer.parseInt(infoArr[1].substring(2));
                this.cutOff = LocalTime.of(hour, minute);

                this.fridayRuleAdd.put(this.cutOff,Float.parseFloat(infoArr[2]));
                break;

            case "view":
                this.viewAdd.put(View.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

        }
    }

    //Checks for price based on pricing rules set 
    public Float queryPrice(AgeGroup ageGroup, Hype hype, CinemaClass cinemaClass, boolean isPH,  Integer dayOfWeek, LocalTime startTime, View view){
        Float price = base; 

        if (this.hypeAdd.containsKey(hype)){
            price += hypeAdd.get(hype);
        }

        if (this.cinemaClassAdd.containsKey(cinemaClass)){
            price += cinemaClassAdd.get(cinemaClass);
        }

        if (this.dayOfWeekAdd.containsKey(dayOfWeek)){
            price += dayOfWeekAdd.get(dayOfWeek);

            if (dayOfWeek == 5 && startTime.isAfter(cutOff)){
                price += fridayRuleAdd.get(cutOff);
            }
        }

        if (this.ageGroupChange.containsKey(ageGroup) && !isPH && startTime.isBefore(cutOff)){
            price = this.ageGroupChange.get(ageGroup); //resets price if ticket is a child of senior ticket for a show before cut off timing and not on a public holiday
        }
        
        if(this.viewAdd.containsKey(view)){
            price += this.viewAdd.get(view);
        }
        return price;
    }


    //Add pricing rule 
    public void addPricingRule(Integer ruleClass, String type, Float val){
        switch(ruleClass){
            case 1: //ageGroup
                try{
                    AgeGroup.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(ageGroupChange.put(AgeGroup.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 2: //hype
                try{
                    Hype.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(hypeAdd.put(Hype.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 3: //cinemaClass
                try{
                    CinemaClass.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(cinemaClassAdd.put(CinemaClass.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 4: //dayOfWeek
                try{
                    Integer.parseInt(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(dayOfWeekAdd.put(Integer.parseInt(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 5: //fridayRule
                Integer hour = Integer.parseInt(type.substring(0,2));
                Integer minute = Integer.parseInt(type.substring(2,4));
                if (hour < 0 || hour > 23 || minute < 0 || minute > 59){
                    System.out.println("Error: Invalid rule type entered");
                    break;
                } else if (fridayRuleAdd.put(LocalTime.of(hour,minute), val) == null){
                    System.out.println("Rule successfully added.");                               
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 6: //view
                try{
                    View.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(viewAdd.put(View.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 7: //quit
                System.out.println("Exiting pricing rule removal menu");
                break;

            default:
                System.out.println("Invalid choice, exiting pricing rule removal menu");
        }
    }
    //Update pricing rule
    public void updatePricingRule(Integer ruleClass, String type, Float val){
        switch(ruleClass){
            case 1: //base
                this.base = val;
                break;
            case 2: //ageGroup
                if (this.ageGroupChange.containsKey(AgeGroup.valueOf(type))){
                    hypeAdd.replace(Hype.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                }
                else{
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 3: //hype
                if (this.hypeAdd.containsKey(Hype.valueOf(type))){
                    hypeAdd.replace(Hype.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                }
                else{
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 4: //cinemaClass
                if (this.cinemaClassAdd.containsKey(CinemaClass.valueOf(type))){
                    cinemaClassAdd.replace(CinemaClass.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 5: //dayOfWeek
                if (this.dayOfWeekAdd.containsKey(Integer.parseInt(type))){
                    dayOfWeekAdd.replace(Integer.parseInt(type), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 6: //fridayRule
                Integer hour = Integer.parseInt(type.substring(0,2));
                Integer minute = Integer.parseInt(type.substring(2,4));
                if (fridayRuleAdd.containsKey(LocalTime.of(hour,minute))){
                    fridayRuleAdd.replace(LocalTime.of(hour,minute), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 7: //view
                if (viewAdd.containsKey(View.valueOf(type))){
                    viewAdd.replace(View.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            default:
                System.out.println("\nInvalid choice, exiting pricing rule removal menu");
        }
    }


    
    //Remove Pricing Rule 
    public void removePricingRule(Integer ruleClass, String type){
        switch(ruleClass){
                
            case 1: //ageGroup
                if(ageGroupChange.remove(AgeGroup.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 2: //hype
                if(hypeAdd.remove(Hype.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 3: //cinemaClass
                if(cinemaClassAdd.remove(CinemaClass.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 4: //dayOfWeek
                if(dayOfWeekAdd.remove(Integer.parseInt(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 5: //fridayRule
                Integer hour = Integer.parseInt(type.substring(0,2));
                Integer minute = Integer.parseInt(type.substring(2,4));
                if(fridayRuleAdd.remove(LocalTime.of(hour,minute)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");                               
                break;

            case 6: //view
                if(viewAdd.remove(View.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 7: //quit
                System.out.println("Exiting pricing rule removal menu");
                break;

            default:
                System.out.println("Invalid choice, exiting pricing rule removal menu");
        }
    }

    //Print all pricing rules 
    public void printAllPricings(){

        System.out.println("\n-------------------Pricing Rules-------------------");
        
        //Base
        System.out.println("Rule Class: Base");
        System.out.println("Type: Base");
        System.out.println("Base price for tickets: $" + String.valueOf(this.base));
        System.out.println("---------------------------------------------------");
        
        
        //Age group rule
        for (Map.Entry<AgeGroup, Float> entry : ageGroupChange.entrySet()){
            System.out.println("Rule Class: Age Group");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Discounted base price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Movie Hype rule
        for (Map.Entry<Hype, Float> entry : hypeAdd.entrySet()){
            System.out.println("Rule Class: Hype");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Cinema class rule
        for (Map.Entry<CinemaClass,  Float> entry : cinemaClassAdd.entrySet()){
            System.out.println("Rule Class: Cinema Class");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Day of week rule
        for (Map.Entry<Integer, Float> entry : dayOfWeekAdd.entrySet()){
            System.out.println("Rule Class: Day of Week");
            System.out.println("Type: "+ String.valueOf(entry.getKey()));
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Friday rules (Matches weekend pricing after a certain cutoff timing)
        for (Map.Entry<LocalTime, Float> entry : fridayRuleAdd.entrySet()){
            LocalTime time = entry.getKey();
            int hour = time.getHour();
            String hourStr = hour < 10 ? "0" : "" + String.valueOf(hour);
            int minute  = time.getMinute();
            String minuteStr = minute < 10 ? "0" : "" + String.valueOf(minute);

            System.out.println("Rule Class: Friday evening surcharges");
            System.out.println("Type : "+ hourStr + minuteStr + "h");
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //View rules
        for (Map.Entry<View, Float> entry : viewAdd.entrySet()){
            System.out.println("Rule Class: View");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
    }

    //Save all changes made
    public void closePricingStore(){
        try(FileWriter writer = new FileWriter(path)){
            
            writer.write(this.pricingHeader);
            
            //Write Rules
            writer.write("\nbase|base|" + String.valueOf(this.base)); 

            //Age group rule
            for (Map.Entry<AgeGroup, Float> entry : ageGroupChange.entrySet()){
               writer.write("\nageGroup|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Movie Hype rule
            for (Map.Entry<Hype, Float> entry : hypeAdd.entrySet()){
               writer.write("\nhype|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Cinema class rule
            for (Map.Entry<CinemaClass,  Float> entry : cinemaClassAdd.entrySet()){
               writer.write("\ncinemaClass|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Day of week rule
            for (Map.Entry<Integer, Float> entry : dayOfWeekAdd.entrySet()){
               writer.write("\ndayOfWeek|" + String.valueOf(entry.getKey()) + "|" + String.valueOf(entry.getValue()));
            }
            //Friday rules (Matches weekend pricing after a certain cutoff timing)
            for (Map.Entry<LocalTime, Float> entry : fridayRuleAdd.entrySet()){
                LocalTime time = entry.getKey();
                int hour = time.getHour();
                String hourStr;
                if (hour < 10){
                    hourStr = "0" + String.valueOf(hour);
                } else {
                    hourStr = String.valueOf(hour);
                }
                int minute  = time.getMinute();
                String minuteStr;
                if (minute < 10){
                    minuteStr = "0" + String.valueOf(minute);
                } else {
                    minuteStr = String.valueOf(minute);
                }
                writer.write("\nfridayRule|" + hourStr + minuteStr + "|" + String.valueOf(entry.getValue()));
            }
            //View rules
            for (Map.Entry<View, Float> entry : viewAdd.entrySet()){
               writer.write("\nview|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}   