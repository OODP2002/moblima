// Done by Mingyang
import java.util.Arrays;
import java.util.HashMap;

public class Layout {
    private int rows;   // Num of rows
    private int columns;    // Num of columns
    private int aisle;      // Empty row (not part of rows)
    private int mainStairway;   // Empty column (not part of col)



    private int[][] layout;

    public Layout(int rows, int columns, int aisle, int mainStairway) {
        this.aisle = aisle;
        this.columns = columns;
        this.rows = rows;
        this.mainStairway = mainStairway;

        layout = new int[rows][columns];
        // Fill array with 0s
        for (int[] row: layout) {
            Arrays.fill(row, 0);
        }
    }

    public HashMap<String, Seat> createSeats() {
        HashMap<String, Seat> seats = new HashMap<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String seatID = generateSeatID(r,c);
                Seat seat = new Seat(seatID);
                seats.put(seatID, seat);
            }
        }
        return seats;
    }

    public String generateSeatID(int r, int c) {
        return String.format("%c%d", r+64, c);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getAisle() {
        return aisle;
    }

    public int getMainStairway() {
        return mainStairway;
    }
}
