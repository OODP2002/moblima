import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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


    //Operations to update pricing store 
    public boolean changeHype(Hype hype, Float newVal){
        if (this.hypeAdd.containsKey(hype)){
            hypeAdd.replace(hype, newVal);
            return true;
        } else return false;
    }

    public boolean changCinemaClass(CinemaClass cinemaClass, Float newVal){
        if (this.cinemaClassAdd.containsKey(cinemaClass)){
            cinemaClassAdd.replace(cinemaClass, newVal);
            return true;
        } else return false;

    }

    public boolean changeDayOfWeek(Integer dayOfWeek, Float newVal){
        if (this.dayOfWeekAdd.containsKey(dayOfWeek)){
            dayOfWeekAdd.replace(dayOfWeek, newVal);
            return true;
        } else return false;
    }

    public boolean changeCutOff(LocalTime oldCutOff, LocalTime newCutoff){
        if(fridayRuleAdd.containsKey(oldCutOff) && cutOff != null){
            Float val = fridayRuleAdd.get(oldCutOff);
            fridayRuleAdd.remove(oldCutOff);
            fridayRuleAdd.put(newCutoff, val);
            return true;
        } else return false;
    }

    public boolean changeFridayRule(LocalTime cutOff, Float newVal){
        if (fridayRuleAdd.containsKey(cutOff)){
            fridayRuleAdd.replace(cutOff, newVal);
            return true;
        }
        else return false;
    }

    public boolean changeView(View view, Float newVal){
        if (viewAdd.containsKey(view)){
            viewAdd.replace(view, newVal);
            return true;
        } else return false;
    }

    //Save all changes made
    public void closePricingStore(){
        try(FileWriter writer = new FileWriter(path)){
            
            writer.write(this.pricingHeader);
            
            //Write Rules
            writer.write("\nbase|base|" + String.valueOf(this.base)); 

            //Age group rule
            for (Map.Entry<AgeGroup, Float> entry : ageGroupChange.entrySet()){
               writer.write("ageGroup|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Movie Hype rule
            for (Map.Entry<Hype, Float> entry : hypeAdd.entrySet()){
               writer.write("hype|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Cinema class rule
            for (Map.Entry<CinemaClass,  Float> entry : cinemaClassAdd.entrySet()){
               writer.write("cinemaClass|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Day of week rule
            for (Map.Entry<Integer, Float> entry : dayOfWeekAdd.entrySet()){
               writer.write("dayOfWeek|" + String.valueOf(entry.getKey()) + "|" + String.valueOf(entry.getValue()));
            }
            //Friday rules (Matches weekend pricing after a certain cutoff timing)
            for (Map.Entry<LocalTime, Float> entry : fridayRuleAdd.entrySet()){
               LocalTime time = entry.getKey();
               int hour = time.getHour();
               String hourStr = hour < 10 ? "0" : "" + String.valueOf(hour);
               int minute  = time.getMinute();
               String minuteStr = minute < 10 ? "0" : "" + String.valueOf(minute);
               writer.write("fridayRule|" + hourStr + minuteStr + "|" + String.valueOf(entry.getValue()));
            }
            //View rules
            for (Map.Entry<View, Float> entry : viewAdd.entrySet()){
               writer.write("view|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   
}   