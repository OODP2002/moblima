import java.time.LocalDateTime;

public class tester {
    public static void main(String[] args){
        Cinema c = new Cinema(5, 6, "DZ1");
        
        Showtime eveningShow = new Showtime("Show123","movie123", "Black Panther",LocalDateTime.of(2022,10,14, 10,34), LocalDateTime.of(2022,10,14,12,34),  VisualType.TWOD, false, c.getNumSeats());
        c.setShowtime(eveningShow);
        c.getShowtimes();
    }
}
