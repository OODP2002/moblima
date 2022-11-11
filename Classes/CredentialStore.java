import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CredentialStore {
    //Attributes
    private HashMap<String, Credential> credentialHashMap = new HashMap<>();
    private String path = "Classes/src/credentials.txt";
    private ArrayList<Credential> credentials = new ArrayList<Credential>();
    private static CredentialStore single_instance = null;
    private TxtReaderWriter credentialReaderWriter = new TxtReaderWriter(path);
    
    //Constructor 
    private CredentialStore(){
        loadCredentialHashMap(credentialReaderWriter.getRawStringFromFile());
    }


    // parse HashMap to ArrayList<String[]>
    private ArrayList<String[]> parseHashMap() {
        List<String[]> arrayListOut = new ArrayList<>();
        Set<String> keys = credentialHashMap.keySet();

        // Iterate over each Credential item
        for (String key: keys) {
            Credential credential = credentialHashMap.get(key);
            ArrayList<String> line = new ArrayList<>();

            line.add(credential.getUsername());
            line.add(credential.getPassword());
            line.add(String.valueOf(credential.getRole()));

            String[] out = new String[line.size()];
            arrayListOut.add(line.toArray(out));
        }
        return (ArrayList<String[]>) arrayListOut;
    }

    // Destructor
    public void closeShowTimeStore() {
        credentialReaderWriter.setRawStringFromFile(parseHashMap());
    }


    private void loadCredentialHashMap(ArrayList<String[]> credentialRawStore) {
        for (String[] line : credentialRawStore) {
            AdminRole admRole = switch (line[2]) {
                case "CinemaStaff" -> AdminRole.CINEMA_STAFF;
                default -> AdminRole.CINEMA_STAFF; // lowest priority
            };
            Credential credential = new Credential(line[0], line[1], admRole);
            credentialHashMap.put(line[0], credential);
        }
    }

    //Return instance of store
    public static CredentialStore getInstance() {
            if (single_instance == null)
                single_instance = new CredentialStore();

            return single_instance;
    }

    // create new user
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

    // Return password given String
    public String getPassword(String username) {
        if (credentialHashMap.containsKey(username))
            return credentialHashMap.get(username).getPassword();
        else
            return null;
    }

    // changing username --> current admin doesnt have the right to change
    public boolean changeUsername(String oldUsername, String newUsername, String password){
        int index = getUserIndex(oldUsername);
        if (index != -1){
            return credentials.get(index).setUsername(newUsername, password); //username change successful or failed
        } else {
            return false; //User not found
        }
    }

    //changing password --> current admin doesnt have the right to change
    public boolean changePassword(String username, String oldPassword, String newPassword){
        int index = getUserIndex(username);
        if (index != -1){
            return credentials.get(index).setPassword(oldPassword, newPassword); //password change successful of failed
        } else {
            return false; //User not found
        }
    }

}