package Java.domain;

import Java.exception.InvalidPegLocationException;
import Java.exception.InvalidShipLocationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    private Game classToTest;
    private MyScanner mockScanner;

    @BeforeEach
    void setUp() {
        classToTest = new GameStub();
        mockScanner = mock(MyScanner.class);
        classToTest.setShip(classToTest.getOpponentsBoard(), ShipType.SUBMARINE, "D2", "R");
        classToTest.setShip(classToTest.getOpponentsBoard(), ShipType.CRUISER, "A5", "D");
    }

    @Test
    void guess_miss() throws InvalidPegLocationException {
        Location location = new Location("B7");
        assertEquals(PegStatus.MISS.getName(), classToTest.guess(classToTest.getOpponentsBoard(), location));
    }

    @Test
    void guess_Hit() throws InvalidPegLocationException {
        Location location = new Location("F2");

        assertEquals(PegStatus.HIT.getName(), classToTest.guess(classToTest.getOpponentsBoard(), location));
    }

    @Test
    void setShip() {
        classToTest.setShip(classToTest.getMyBoard(), ShipType.SUBMARINE, "A2", "D");

        assertEquals(PegStatus.EMPTY, classToTest.getMyBoard().getGrid()[0][0].getStatus());
        assertEquals(PegStatus.OCCUPIED, classToTest.getMyBoard().getGrid()[0][1].getStatus());
        assertEquals(PegStatus.OCCUPIED, classToTest.getMyBoard().getGrid()[0][2].getStatus());
        assertEquals(PegStatus.OCCUPIED, classToTest.getMyBoard().getGrid()[0][3].getStatus());
        assertEquals(PegStatus.EMPTY, classToTest.getMyBoard().getGrid()[0][4].getStatus());
    }

    @Test
    void setShip_OffBoard_ThrowsError() {
        assertFalse(classToTest.setShip(classToTest.getMyBoard(), ShipType.SUBMARINE, "I2", "R"));
    }

    @Test
    void getRandomLocation() throws InvalidPegLocationException {
        for (int i = 0; i < 10000; i++) {
            Location randomLocation = classToTest.getRandomLocation();
            if (randomLocation.getRowIndex() > 9 || randomLocation.getRowIndex() < 0) {
                fail();
            }
        }
    }

    @Test()
    void validateDirection_R() {
        Assertions.assertDoesNotThrow(() -> classToTest.validateDirection("R"));
    }

    @Test()
    void validateDirection_D() {
        Assertions.assertDoesNotThrow(() -> classToTest.validateDirection("D"));
    }

    @Test()
    void validateDirection_notRnotD() {
        Assertions.assertThrows(InvalidShipLocationException.class, () -> classToTest.validateDirection("F"));
    }

    @Test()
    void validateDirection_empty() {
        Assertions.assertThrows(InvalidShipLocationException.class, () -> classToTest.validateDirection(""));
    }

    @Test
    void enterGuess_hit() {
        when(mockScanner.askForInput("Guess location")).thenReturn("A6");

        assertEquals(PegStatus.HIT.getName(), classToTest.enterGuess());
    }

    @Test
    void enterGuess_miss() {
        when(mockScanner.askForInput("Guess location")).thenReturn("A4");

        assertEquals(PegStatus.MISS.getName(), classToTest.enterGuess());
    }

    @Test
    void enterGuess_invalidGuess() {
        when(mockScanner.askForInput("Guess location")).thenReturn("Hello");

        assertEquals("Invalid guess.", classToTest.enterGuess());
    }

    public class GameStub extends Game {
        @Override
        protected MyScanner getMyScanner() { return mockScanner; }
    }
}