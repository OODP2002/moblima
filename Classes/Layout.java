// Done by Mingyang
import java.util.Arrays;

public class Layout {
    private int rows;
    private int columns;
    private int aisle;
    private int mainStairway;
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

    public void printLayout() {
        String s1 = "-";
        String s = s1.repeat((columns * 4 - 4) / 2);
        System.out.println(s + "SCREEN" + s);

        // Print column numbers
        System.out.printf(" ");
        for (int i = 1; i <= columns; i++) {
            if (i == aisle)
                System.out.printf("    ");
            else
                System.out.printf("[%02d]", i);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            // Print row letters
            System.out.printf("%c", i+65);

            // Print seats, [  ] for empty [XX] for taken
            for (int j = 0; j < columns; j++) {
                if (j == aisle-1)
                    System.out.print("    ");
                else if (layout[i][j] == 0) {
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
            if (i == aisle) {
                System.out.print("    ");
            }
            else
                System.out.printf("[%02d]", i);
        }
        System.out.println();
    }
}
