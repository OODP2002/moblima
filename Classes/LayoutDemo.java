public class LayoutDemo {
    public static void main(String[] args) {
        Cinema cinema = CinemaStore.getInstance().getCinema("0102");
        System.out.println(cinema.getCinemaID());
        System.out.println(cinema.getCinemaClass());
        Layout layout = cinema.getLayout();
        layout.printLayout();
    }
}
