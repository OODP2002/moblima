import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class SettingStore {
    private HashMap<String, String> settings = new HashMap<>();
    private final String FILE_SOURCE = "Classes/src/settings.txt";
    private static SettingStore single_instance = null;
    Scanner sc = new Scanner(System.in);
    private TxtReaderWriter settingReaderWriter = new TxtReaderWriter(FILE_SOURCE);

    // Retrieve relevant settings from setting store
    public String getSetting(String setting) {
        return settings.get(setting);
    }

    // Set relevant setting into setting store
    public void setSetting(String setting, String option) {
        settings.put(setting, option);
    }

    private SettingStore() {
        loadSettings(settingReaderWriter.getRawStringFromFile());
    }

    // Destructor
    public void closeSettingStore() {
        settingReaderWriter.setRawStringFromFile(parseHashMap());
    }

    public static SettingStore getInstance(){
        if (single_instance == null)
            single_instance = new SettingStore();

        return single_instance;
    }

    private void loadSettings(ArrayList<String[]> settingRawStore) {
        for (String[] line: settingRawStore) {
            settings.put(line[0], line[1]);
        }
    }

    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        for (String key: settings.keySet()) {
            ArrayList<String> line = new ArrayList<>();
            line.add(key);
            line.add(settings.get(key));
            String[] out = new String[line.size()];
            arrayListOut.add(line.toArray(out));
        }
        return arrayListOut;
    }
}
