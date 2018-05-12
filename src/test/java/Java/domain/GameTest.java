package Java.domain;

import Java.exception.InvalidPegLocationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GameTest {
    private Game classToTest;

    @BeforeEach
    void setUp() {
        classToTest = new Game();
    }

    @Test
    void guess_miss() throws InvalidPegLocationException {
        Location location = new Location("B7");
        assertEquals(PegStatus.MISS.getName(), classToTest.guess(classToTest.getOpponentsBoard(), location));
    }

    @Test
    void guess_Hit() throws InvalidPegLocationException {
        classToTest.setShip(classToTest.getOpponentsBoard(), ShipType.SUBMARINE, "D2", "R");
        Location location = new Location("F2");

        assertEquals(PegStatus.HIT.getName(), classToTest.guess(classToTest.getOpponentsBoard(), location));
    }

    @Test
    void setShip() {
        classToTest.setShip(classToTest.getMyBoard(), ShipType.SUBMARINE, "A1", "D");

        assertEquals(PegStatus.OCCUPIED, classToTest.getMyBoard().getGrid()[0][0].getStatus());
        assertEquals(PegStatus.OCCUPIED, classToTest.getMyBoard().getGrid()[0][1].getStatus());
        assertEquals(PegStatus.OCCUPIED, classToTest.getMyBoard().getGrid()[0][2].getStatus());
        assertEquals(PegStatus.EMPTY, classToTest.getMyBoard().getGrid()[0][3].getStatus());
    }

    @Test
    void getRandomLocation() throws InvalidPegLocationException {
        for(int i=0; i<10000; i++) {
            Location randomLocation = classToTest.getRandomLocation();
            if(randomLocation.getRowIndex() > 9 || randomLocation.getRowIndex() < 0){
                System.out.println("Row #" + randomLocation.getRowIndex());
                fail();
            }
        }
    }
}