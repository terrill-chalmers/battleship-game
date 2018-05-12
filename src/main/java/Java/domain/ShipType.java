package Java.domain;

import Java.utility.DisplayUtil;
import org.apache.commons.lang.WordUtils;

public enum ShipType {
    DESTROYER(2),
    SUBMARINE(3),
    CRUISER(3),
    BATTLESHIP(4),
    CARRIER(5);

    private final int size;

    ShipType(final int size) { this.size = size; }

    public int getSize() { return size; }

    public String getName() { return DisplayUtil.toTitleCase(this.name()); }
}
