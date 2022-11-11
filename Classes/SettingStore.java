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

    private SettingStore() {
        loadSettings(settingReaderWriter.getRawStringFromFile());
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
        for (String line: settings.values()) {
            System.out.println(line); // for debugging
            ArrayList<String> line1 = new ArrayList<>();
            String[] out = new String[line1.size()];
            arrayListOut.add(line1.toArray(out));
        }
        return arrayListOut;
    }
}
