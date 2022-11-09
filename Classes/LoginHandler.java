import java.util.Scanner;

public class LoginHandler {
    public Admin login(){
        Scanner sc = new Scanner(System.in); 
        
        //Admin object if login in successful
        Admin admObj = null; 
        
        //user input
        String username, password;
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
                username = sc.next();
                System.out.print("Enter Password: ");
                password = sc.next();
                
                admObj = CredentialStore.getInstance().validate(username, password);
                
                if (admObj == null){
                    System.out.println("Error: Invalid Credential");
                } else {
                    System.out.println("Logged in. Welcome " + username + ".");
                    break;
                }
            } 
            
            else if (option == 2){
                return null;
            } 
            
            else {
                System.out.println("Error: Incorrect option. Please try again.");
            }
        }
        sc.close();
        return admObj; // returns null object if login fails 
    }
}