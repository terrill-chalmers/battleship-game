package Java.domain;

import Java.exception.InvalidPegLocationException;

import java.util.Arrays;

public class Location {
    private int columnIndex;
    private int rowIndex;
    private final static String[] COLUMN_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final static String[] ROW_NAMES = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public Location(String location) throws InvalidPegLocationException {
        validateLocation(location);
        this.columnIndex = Arrays.asList(COLUMN_NAMES).indexOf(location.substring(0, 1));
        if (location.length() > 2) {
            this.rowIndex = Arrays.asList(ROW_NAMES).indexOf(location.substring(1, 3));
        } else {
            this.rowIndex = Arrays.asList(ROW_NAMES).indexOf(location.substring(1, 2));
        }
    }

    private void validateLocation(String location) throws InvalidPegLocationException {
        String column = location.substring(0, 1);
        int row;
        if (location.length() > 2) {
            row = Arrays.asList(ROW_NAMES).indexOf(location.substring(1, 3));
        } else {
            row = Arrays.asList(ROW_NAMES).indexOf(location.substring(1, 2));
        }
        if (!Arrays.asList(COLUMN_NAMES).contains(column) ||
                !Arrays.asList(ROW_NAMES).contains(String.valueOf(row + 1))) {
            throw new InvalidPegLocationException();
        }
    }

    public int getColumnIndex() { return columnIndex; }

    public int getRowIndex() { return rowIndex; }
}
