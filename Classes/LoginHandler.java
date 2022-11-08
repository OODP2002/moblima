import java.util.Scanner;

public class LoginHandler {
    private String username;
    private String password;
    

    LoginHandler(){
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter Username: ");
        this.username = sc.next();
        System.out.print("Enter Password: ");
        this.password = sc.next();
        sc.close();
    }

    public Admin Login(){
        return CredentialStore.getInstance().validate(this.username, this.password);
    }
}
