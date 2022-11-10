import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;

public class MovieGoer implements Person, ReviewHandler, MovieQuery, TicketHandler {
    private String name;
    private MovieStore movStore = MovieStore.getInstance();
    private String email;
    private Integer mobile;
    Scanner sc = new Scanner(System.in);


    // Initialization: get Movie Goer details
    public MovieGoer() {
        System.out.println("Welcome to Movie Goer registration module");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        System.out.print("Enter mobile number: ");
        this.mobile = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter email address: ");
        this.email = sc.nextLine();
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getMobile() {
        return this.mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }



    public void showHistory() {
        // Use name attribute to match with ticketStore
        TicketStore tixStore = TicketStore.getInstance();
        System.out.println("------Past Ticket History------");
        tixStore.listPastPurchases(name, email, mobile);
    }
}
