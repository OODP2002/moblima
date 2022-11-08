import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CredentialStore {
    //Attributes 
    private ArrayList<Credential> credentials = new ArrayList<Credential>();
    private static CredentialStore instance = new CredentialStore();
    private String path = System.getProperty("user.dir") + ("/src/credentials.txt");
    
    //Constructor 
    private CredentialStore(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String header = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                credentials.add(createCredentialObj(line)); //Add pricing object to special occasion array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            //System.out.println(err.getStackTrace());
            System.out.println("Error: Credential list not found");
        }

    }

    //Operations 
    //Return instance of store
    public static CredentialStore getInstance(){
        return instance;
    }

    //Creates a new credential object based on 
    private Credential createCredentialObj(String info){
        String[] infoArr =  info.split("\\|");

        AdminRole admRole;
        switch(infoArr[2]){
            case "CinemaStaff":
                admRole = AdminRole.CINEMASTAFF;
                break;
            default: 
                admRole = AdminRole.CINEMASTAFF; // lowest priority
        }
        return new Credential(infoArr[0], infoArr[1], admRole);
    }

    //create new user
    public void newUser(Credential newCredential){
        this.credentials.add(newCredential);
        return;
    }
   
    //search for index of user in array list 
    private int getUserIndex(String username){
        for (int i = 0; i < credentials.size(); i++){
            if(credentials.get(i).getUsername().equals(username)){
                return i;
            }
        }
        System.out.println("Username not found.");
        return -1;
    }

    //validate credentials for login (poly)
    public Admin validate(String username, String password){
        int index = getUserIndex(username);
        if (index != -1 && credentials.get(index).check(password)){
            Admin admObj = null;
            switch(credentials.get(index).getRole()){
                case CINEMASTAFF:
                    admObj = new CinemaStaff(username);
                    break;
            }
            System.out.println("Successful");
            return admObj;
        } else {
            return null; //User does not exist 
        }
    }

    //changing username
    public boolean changeUsername(String oldUsername, String newUsername, String password){
        int index = getUserIndex(oldUsername);
        if (index != -1){
            return credentials.get(index).setUsername(newUsername, password); //username change successful or failed
        } else {
            return false; //User not found
        }
    }

    //changing password 
    public boolean changePassword(String username, String oldPassword, String newPassword){
        int index = getUserIndex(username);
        if (index != -1){
            return credentials.get(index).setPassword(oldPassword, newPassword); //password change successful of failed
        } else {
            return false; //User not found
        }
    }

    //Overwrite old credential list with a new set of credentials
    public void writeToCredentialsFile(){
        try{
            FileWriter writer = new FileWriter(path);
            for (int i = -1; i < this.credentials.size(); i++){
                writer.write("\n" + this.credentials.get(i).toString());
            }
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
