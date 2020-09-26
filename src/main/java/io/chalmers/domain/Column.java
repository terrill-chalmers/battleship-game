package io.chalmers.domain;

import java.util.Random;

public enum Column {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9);

    Column(int index) { this.index = index; }

    private int index;

    public int getIndex() { return index; }

    public Column getColumnByIndex(int index) { return Column.values()[index]; }

    public static Column getRandomColumn() { return values()[new Random().nextInt(values().length)]; }
}
