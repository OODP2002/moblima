import java.util.Arrays;

// Done by Mingyang
public class Layout {
    private int rows;
    private int columns;
    private int aisle;
    private int[][] layout;

    public Layout(int rows, int columns, int aisle) {
        this.aisle = aisle;
        this.columns = columns;
        this.rows = rows;

        layout = new int[rows][columns];
        // Fill array with 0s
        for (int[] row: layout) {
            Arrays.fill(row, 0);
        }
    }

    public void printLayout() {
        System.out.println("SCREEN -->");

        // Print column numbers
        System.out.printf(" ");
        for (int i = 1; i <= columns; i++) {
            System.out.printf("[%02d]", i);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            // Print row letters
            System.out.printf("%c", i+65);

            // Print seats, [  ] for empty [XX] for taken
            for (int j = 0; j < columns; j++) {
                if (layout[i][j] == 0) {
                    System.out.printf("[  ]");
                }
                else {
                    System.out.print("[XX]");
                }
            }
            System.out.printf("%c\n", i+65);
        }

        // Print column numbers
        System.out.printf(" ");
        for (int i = 1; i <= columns; i++) {
            System.out.printf("[%02d]", i);
        }
        System.out.println();
    }
}
