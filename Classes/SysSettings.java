// Allow for only retrieving settings
public interface SysSettings {
    default String getListBy() {
        return SettingStore.getInstance().getSetting("list-by");
    }
}
