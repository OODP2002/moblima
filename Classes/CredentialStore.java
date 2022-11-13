import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CredentialStore {
    //Attributes
    private HashMap<String, Credential> credentialHashMap = new HashMap<>();
    private String path = "Classes/src/credentials.txt";
    private static CredentialStore single_instance = null;
    private TxtReaderWriter credentialReaderWriter = new TxtReaderWriter(path);
    
    //Constructor
    private CredentialStore(){
        loadCredentialHashMap(credentialReaderWriter.getRawStringFromFile());
    }

    //Return instance of store
    public static CredentialStore getInstance() {
            if (single_instance == null)
                single_instance = new CredentialStore();

            return single_instance;
    }

    // Return password given String
    public String getPassword(String username) {
        if (credentialHashMap.containsKey(username))
            return credentialHashMap.get(username).getPassword();
        else
            return null;
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
}