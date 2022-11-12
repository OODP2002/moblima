import java.util.Scanner;

public class LoginHandler {
    Scanner sc = new Scanner(System.in);
    CinemaStaff cinemaStaff;
    public CinemaStaff login(){
        //user input
        int option;

        do {
            System.out.println("\n----------------------------");
            System.out.println("Please select a choice below");
            System.out.println("1 - Cinema staff login");
            System.out.println("2 - Exit");
            System.out.println("----------------------------");
            System.out.print("\nChoice: ");
            try{
            option = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 2).\n");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (option) {
                case 1 -> {
                    try{
                        System.out.print("\nEnter Username: ");
                        String username = sc.nextLine();
                        cinemaStaff = new CinemaStaff(username);
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                    

                        if (!cinemaStaff.validate(password)){
                            System.out.println("\nError: Invalid Credential");
                            return null;
                        } else {
                            System.out.println("\nLogged in. Welcome " + username + ".");
                            return cinemaStaff;
                        }
                    } catch (Exception err){
                        System.out.println(err.getMessage());
                    }
                }
                case 2 -> {
                    return null;
                }
                default -> System.out.println("Error: Incorrect option. Please try again.");
            }
        } while (true);
    }
}