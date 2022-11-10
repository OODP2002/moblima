public class ShowTimeStoreDemo {
    public static void main(String[] args) {
        ShowTimeStore sts = ShowTimeStore.getInstance();
        ShowTime showTime = sts.getShowTime("0001aaa");
        showTime.printShowTime();

        // Add new show time module
        sts.addShowTime();


        // Update showtime module
        sts.updateShowtime();

        // Call to update txt file
        sts.closeShowTimeStore();
    }
}
