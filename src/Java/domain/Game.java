package Java.domain;

public class Game {

    private String[] COLUMN_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private String[] ROW_NAMES = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private Grid board = new Grid(COLUMN_NAMES.length,ROW_NAMES.length);


    public Game() {
        initializeBoard();
    }

    private void

    private void initializeBoard() {
        for (int column = 0; column < COLUMN_NAMES.length; column++) {
            for (int row = 0; row < ROW_NAMES.length; row++) {
                board[column][row] = Peg.EMPTY;
            }
        }
    }
}
