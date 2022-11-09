public class ShowTimeStoreDemo {
    public static void main(String[] args) {
        ShowTimeStore sts = ShowTimeStore.getInstance();
        ShowTime showTime = sts.getShowTime("0001aaa");
        showTime.printShowTime();

        sts.addShowTime();

        sts.writeFile();
    }
}
