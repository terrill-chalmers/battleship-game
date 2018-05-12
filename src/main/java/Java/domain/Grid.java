package Java.domain;

public class Grid {
    private static final int COLUMN_SIZE = 10, ROW_SIZE = 10;
    private Peg[][] grid;

    public Grid() {
        grid = new Peg[COLUMN_SIZE][ROW_SIZE];
        initializeGrid();
    }

    public Peg[][] getGrid() { return grid; }

    private void initializeGrid() {
        for (int column = 0; column < COLUMN_SIZE; column++) {
            for (int row = 0; row < ROW_SIZE; row++) {
                Peg peg = new Peg();
                peg.setStatus(PegStatus.EMPTY);
                grid[column][row] = peg;
            }
        }
    }
}
