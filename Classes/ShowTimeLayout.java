import java.util.HashMap;
import java.util.List;

/* Created by Mingyang
*  Purpose of class: know which seats are taken and which are not
*  Has a HashMap containing all Seats in this ShowTime */
public class ShowTimeLayout {
    private final HashMap<String, Seat> seatHashMap;    // key=SEAT_ID
    private final int rows;
    private final int columns;
    private final int aisle;
    private final int mainStairway;
    private final Layout layout;

    public ShowTimeLayout(String showtimeID) {

        //Generating Cinema layout from cineplexes.txt
        this.layout = LayoutStore.getInstance().getLayout(showtimeID.substring(0,4));
        this.seatHashMap = layout.createSeats();

        this.rows = layout.getRows();
        this.columns = layout.getColumns();
        this.aisle = layout.getAisle();
        this.mainStairway = layout.getMainStairway();

        //Get occupied seats from seats.txt
        List<String> occupiedSeats = SeatStore.getInstance().getSeatsForShowTime(showtimeID);
        if (occupiedSeats != null){
            for(int i = 0; i < occupiedSeats.size(); i++){
                seatHashMap.get(occupiedSeats.get(i)).setAvail(false); //Marking seats as unavailable
            }
        }

    }

    public HashMap<String, Seat> getSeatHashMap() {
        return seatHashMap;
    }

    public Seat getSeat(String seatID) {
        return seatHashMap.get(seatID);
    }

    public void printLayout() {
        String s1 = "-";
        String s = s1.repeat((columns * 4 - 4) / 2);
        System.out.println(s + "LEGEND" + s);
        System.out.println("[  ]: Seat available");
        System.out.println("[XX]: Seat unavailable");
        System.out.println(s + "------" + s);
        System.out.println(s + "SCREEN" + s);

        // Print column numbers
        printColumnNumbers();

        for (int r = 1; r <= rows; r++) {
            if (r == aisle) {
                System.out.println();
                continue;
            }
            // Print row letters
            System.out.printf("%c", r+64);

            // Print seats, [  ] for empty [XX] for taken
            for (int c = 1; c <= columns; c++) {
                String seatID = layout.generateSeatID(r, c);
                if (c == mainStairway)
                    System.out.print("    ");
                else if (getSeat(seatID).getAvail()) {
                    System.out.print("[  ]");
                }
                else {
                    System.out.print("[XX]");
                }
            }
            System.out.printf("%c\n", r+64);
        }

        // Print column numbers
        printColumnNumbers();
    }

    private void printColumnNumbers() {
        System.out.print(" ");
        for (int c = 1; c <= columns; c++) {
            if (c == mainStairway) {
                System.out.print("    ");
            }
            else
                System.out.printf("[%02d]", c);
        }
        System.out.println();
    }
}
