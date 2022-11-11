import java.util.Scanner;

public class LoginHandler {
    public CinemaStaff login(){
        Scanner sc = new Scanner(System.in);
        CinemaStaff cinemaStaff;
        
        //user input
        int option;

        while (true){
            System.out.println("-----------------------------");
            System.out.println("Please select an option below");
            System.out.println("1 - Cinema staff login");
            System.out.println("2 - Exit");
            System.out.println("-----------------------------\n");
            System.out.print("Option: ");
            option = sc.nextInt();

            
            if (option == 1){
                System.out.print("Enter Username: ");
                String username = sc.nextLine();
                cinemaStaff = new CinemaStaff(username);
                System.out.print("Enter Password: ");
                String password = sc.nextLine();
                
                if (!cinemaStaff.validate(password)){
                    System.out.println("Error: Invalid Credential");
                    return null;
                } else {
                    System.out.println("Logged in. Welcome " + username + ".");
                    break;
                }
            }
            
            else if (option == 2) {
                return null;
            } 
            
            else {
                System.out.println("Error: Incorrect option. Please try again.");
            }
        }
        sc.close();
        return cinemaStaff; // returns null object if login fails
    }
}