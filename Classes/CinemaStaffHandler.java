import java.util.Scanner;
import java.util.InputMismatchException;

public class CinemaStaffHandler {
    private CinemaStaff cinemaStaff;
    
    public CinemaStaffHandler(CinemaStaff cinemaStaff){
        this.cinemaStaff = cinemaStaff;
    }
    
    public boolean start(){
        Scanner sc = new Scanner(System.in);

        int option;
        
        //While loop escape controls 
        boolean root, opt1, opt2, opt3, opt4, opt5;
        root = opt1 = opt2 = opt3 = opt4 = opt5 = true;
        
        while(root){
            System.out.println("-----------------------------");
            System.out.println("Option Available: (1-5):");
            System.out.println("1 - Ticket pricing ");
            System.out.println("2 - Special occasion");
            System.out.println("3 - Movies");
            System.out.println("4 - Showtimes");
            System.out.println("5 - Listing Options ");
            System.out.println("6 - Quit Admin Module");
            System.out.println("-----------------------------\n");
            
            //User inputs option
            System.out.print("Select an option: ");
            try{
                option = sc.nextInt();
                
                switch(option){
                    
                    case 1: // Ticket Pricing
                        while(opt1){

                            //Helper Variables to be used in Ticket Pricing Option
                            String pricingID, cinemaLevel, view, ageGroup, startTime, endTime, dayofWeek, isPreferred, price;

                            System.out.println("-----------------------------");
                            System.out.println("Select an option below (1-5):");
                            System.out.println("1 - Create new pricing rule");
                            System.out.println("2 - Remove pricing");
                            System.out.println("3 - Print all pricings");
                            System.out.println("4 - Quit");
                            System.out.println("-----------------------------");
                            
                            System.out.print("Select an option: ");

                            try{
                                option = sc.nextInt();
                                System.out.println("\n");
                                switch(option){
                                    case 1: //Pricing object exists? Update Pricing object : Create new Pricing object
                                        
                                        System.out.print("Enter Cinema Type (PLATINUM / GOLD / STANDARD): ");
                                        cinemaLevel = sc.next();
                                        System.out.print("Enter View Type (2D / 3D): ");
                                        view = sc.next();
                                        System.out.print("Enter Age Group (CHILD / ADULT / SENIOR): ");
                                        ageGroup = sc.next();
                                        System.out.print("Enter Start Time (hh:mm): ");
                                        startTime = sc.next();
                                        System.out.print("Enter End Time (hh:mm): ");
                                        endTime = sc.next();
                                        System.out.print("Enter Day of Week (1-7): ");
                                        dayofWeek = sc.next();
                                        System.out.print("Is Preferred Customer? (True / False): ");
                                        isPreferred = sc.next();
                                        System.out.print("Enter Price : $");
                                        price = sc.next();

                                        //Creates a temporary pricingID for Pricing object. Pricing object will take on this pricingID if this rule does not exist within current pricing rules
                                        pricingID = PricingStore.getInstance().numPricings().toString();
                                        this.cinemaStaff.editPricing(new Pricing(pricingID, cinemaLevel, view, ageGroup, startTime, endTime, dayofWeek, isPreferred, price));
                                        break;

                                    case 2: //Remove Pricing 
                                        System.out.print("Enter Pricing ID to be removed: ");
                                        pricingID = sc.next();
                                        this.cinemaStaff.removePricing(Integer.parseInt(pricingID));
                                        break;

                                    case 3: //Print all Pricings
                                        this.cinemaStaff.printPricingList();
                                        break;

                                    case 4: //Quit
                                        opt1 = false;
                                        break;
                                    default: 
                                        System.out.println("Please select a valid option!\n");
                                }
                
                            } catch (InputMismatchException err){
                                //System.out.println(err.getMessage());
                                System.out.println("Error: Invalid input");
                                System.out.println("Please try again.\n");
                            }
                        }
                        break;

                    case 2: //Special Occasions
                        while(opt2){
                            //Helper Variables for option 2 -- Special Occasion
                            String name, date;
                        
                            System.out.println("-----------------------------");
                            System.out.println("Select an option below (1-4):");
                            System.out.println("1 - Create new special occasion ");
                            System.out.println("2 - Update special occasion");
                            System.out.println("3 - Remove speical occasion ");
                            System.out.println("4 - Print all special occasions");
                            System.out.println("5 - Quit");
                            System.out.println("-----------------------------");
                            
                            System.out.print("Select an option: ");
                            try{
                                option = sc.nextInt();
                                switch(option){
                                    
                                    case 1: //Create special occasion
                                        System.out.print("Enter occasion name: ");
                                        name = sc.next();
                                        System.out.print("Enter date (DD-MM): ");
                                        date = sc.next();
                                        this.cinemaStaff.addSpecialOccasion(date,name);
                                        break;
                                    case 2: //Update special occasion
                                        break;
                                    case 3: //Remove special occasion 
                                        System.out.print("Enter occasion name: ");
                                        name = sc.next();
                                        System.out.print("Enter date (DD-MM): ");
                                        date = sc.next();
                                        this.cinemaStaff.removeSpecialOccasion(date, name); 
                                        break;
                                    case 4:
                                        this.cinemaStaff.printSpecialOccasionList();
                                    case 5: //Quit
                                        opt2 = false;
                                        break;
                                    default:
                                        System.out.println("Please select a valid option!\n");
                                }
                
                            } catch (InputMismatchException err){
                                //System.out.println(err.getMessage());
                                System.out.println("Error: Invalid input");
                                System.out.println("Please try again.\n");
                            }
                        }
                        break;

                    case 3: //Movies 
                        while(opt3){
                            System.out.println("-----------------------------");
                            System.out.println("Select an option below (1-4):");
                            System.out.println("1 - Create new movies");
                            System.out.println("2 - Update movies");
                            System.out.println("3 - Remove movies");
                            System.out.println("4 - Quit");
                            System.out.println("-----------------------------");
                            
                            System.out.print("Select an option: ");
                            try{
                                option = sc.nextInt();
                                switch(option){
                                    
                                    case 1: //Create movies
                                        break;
                                    case 2: //Update movies
                                        break;
                                    case 3: //Remove movies 
                                        break;
                                    case 4: //Quit
                                        opt3 = false;
                                        break;
                                    default:
                                    System.out.println("Please select a valid option!\n");
                                }
                
                            } catch (InputMismatchException err){
                                //System.out.println(err.getMessage());
                                System.out.println("Error: Invalid input");
                                System.out.println("Please try again.\n");
                            }
                        }
                        break;

                    case 4:
                        while(opt4){
                            System.out.println("-----------------------------");
                            System.out.println("Select an option below (1-4):");
                            System.out.println("1 - Create new showtimes");
                            System.out.println("2 - Update showtimes");
                            System.out.println("3 - Remove showtimes");
                            System.out.println("4 - Quit");
                            System.out.println("-----------------------------");
                            
                            System.out.print("Select an option: ");
                            try{
                                option = sc.nextInt();
                                switch(option){
                                    
                                    case 1: //Create showtimes
                                        break;
                                    case 2: //Update showtimes
                                        break;
                                    case 3: //Remove showtimes 
                                        break;
                                    case 4: //Quit
                                        opt4 = false;
                                        break;
                                    default:
                                    System.out.println("Please select a valid option!\n");
                                }
                
                            } catch (InputMismatchException err){
                                //System.out.println(err.getMessage());
                                System.out.println("Error: Invalid input");
                                System.out.println("Please try again.\n");
                            }
                        }
                        break;

                    case 5:
                        while(opt5){
                            System.out.println("-----------------------------");
                            System.out.println("Select an option below (1-4):");
                            System.out.println("1 - Allow both listing options");
                            System.out.println("2 - List by sales only");
                            System.out.println("3 - List by reviews only");
                            System.out.println("4 - Quit");
                            System.out.println("-----------------------------");
                            
                            System.out.print("Select an option: ");
                            try{
                                option = sc.nextInt();
                                switch(option){
                                    
                                    case 1: //Allow both option
                                        break;
                                    case 2: //List by sales only
                                        break;
                                    case 3: //List by sales only
                                        break;
                                    case 4: //Quit
                                        opt5 = false;
                                        break;
                                    default:
                                    System.out.println("Please select a valid option!\n");
                                }
                
                            } catch (InputMismatchException err){
                                //System.out.println(err.getMessage());
                                System.out.println("Error: Invalid input");
                                System.out.println("Please try again.\n");
                            }
                        }
                        break;

                    case 6:
                        root = false;
                        break;

                    default: 
                        System.out.println("Please select a valid option!");
                }

            } catch (InputMismatchException err){
                //System.out.println(err.getMessage());
                System.out.println("Error: Invalid input");
                System.out.println("Please try again.\n");
            }
        }
        
        sc.close();
        return true;
    }

}