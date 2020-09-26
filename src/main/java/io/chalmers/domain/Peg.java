package io.chalmers.domain;

public class Peg {
    private int columnIndex;
    private int rowIndex;
    private PegStatus status;

    public int getColumnIndex() { return columnIndex; }

    public void setColumnIndex(int columnIndex) { this.columnIndex = columnIndex; }

    public int getRowIndex() { return rowIndex; }

    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; }

    public PegStatus getStatus() { return status; }

    public void setStatus(PegStatus status) { this.status = status; }
}
