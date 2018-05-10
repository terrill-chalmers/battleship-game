package Java.domain;

public class Grid {
    private Peg[] columns;
    private Peg[] rows;

    public Grid(Peg[] columns, Peg[] rows) { this.columns = columns; this.rows = rows; }

    public Peg[] getColumns() { return columns; }

    public void setColumns(Peg[] columns) { this.columns = columns; }

    public Peg[] getRows() { return rows; }

    public void setRows(Peg[] rows) { this.rows = rows; }
}
