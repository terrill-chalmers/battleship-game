package Java.domain;

import Java.exception.InvalidPegLocationException;
import Java.exception.InvalidShipLocationException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Game {
    private Grid myBoard;
    private Grid opponentsBoard;

    public Game() {
        myBoard = new Grid();
        opponentsBoard = new Grid();
    }

    public void playGame() {
        setShips(myBoard);
        boolean gameOver;

        do {
            System.out.println(enterGuess());
            gameOver = checkForGameOver();
        } while (!gameOver);
    }

    private boolean checkForGameOver() {
        return false;
    }

    protected void setShips(Grid board) {
        EnumSet.allOf(ShipType.class).forEach(ship -> enterShipLocation(board, ship));
    }

    protected void enterShipLocation(Grid board, ShipType shipType) {
        boolean valid = false;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter starting location for " + shipType.getName());
            String startingLocation = scanner.nextLine();
            System.out.println("Enter direction for " + shipType.getName() + " R(for Right) or D(for Down)");
            String direction = scanner.nextLine();

            if (!setShip(board, shipType, startingLocation, direction)) {
                System.out.println("That is not a valid location for the " + shipType.getName());
            } else {
                valid = true;
            }

        } while (!valid);
    }

    protected boolean setShip(Grid board, ShipType shipType, String startingLocation, String direction) {
        boolean locationOk;
        try {
            Location start = new Location(startingLocation);
            validateDirection(direction);
            List<Peg> proposedPegs = new ArrayList<>();


            for (int offset = 0; offset < shipType.getSize(); offset++) {
                int currentColumnIndex = start.getColumnIndex();
                int currentRowIndex = start.getRowIndex();

                if (direction.equalsIgnoreCase("R")) {
                    currentColumnIndex += offset;
                } else {
                    currentRowIndex += offset;
                }

                Peg currentPeg = new Peg();
                currentPeg.setColumnIndex(currentColumnIndex);
                currentPeg.setRowIndex(currentRowIndex);

                board.getGrid()[currentColumnIndex][currentRowIndex] = currentPeg;
                if (validateLocationIsAvailable(currentPeg)) {
                    proposedPegs.add(currentPeg);
                }
            }

            markShipPegsAsOccupied(board, shipType.getSize(), proposedPegs);
            locationOk = true;

        } catch (InvalidPegLocationException | InvalidShipLocationException ex) {
            locationOk = false;
        }

        return locationOk;
    }

    protected void markShipPegsAsOccupied(Grid board, int shipSize, List<Peg> proposedPegs) {
        if (proposedPegs.size() == shipSize) {
            for (Peg currentPeg : proposedPegs) {
                board.getGrid()[currentPeg.getColumnIndex()][currentPeg.getRowIndex()].setStatus(PegStatus.OCCUPIED);
            }
        }
    }

    protected boolean validateLocationIsAvailable(Peg proposedPeg) {
        return true;
    }

    protected void validateDirection(String direction) throws InvalidShipLocationException {
        if (!direction.toUpperCase().equals("R") && !direction.toUpperCase().equals("D")) {
            throw new InvalidShipLocationException();
        }
    }

    protected String guess(Grid board, Location location) {
        Peg guessPeg = board.getGrid()[location.getColumnIndex()][location.getRowIndex()];
        PegStatus status = guessPeg.getStatus();

        if (status == PegStatus.OCCUPIED) {
            status = PegStatus.HIT;
        } else {
            status = PegStatus.MISS;
        }

        guessPeg.setStatus(status);

        return status.getName();
    }

    protected String enterGuess() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Guess location");

            Location location = new Location(scanner.nextLine());

            return guess(opponentsBoard, location);
        } catch (InvalidPegLocationException e) {
            return "Invalid guess.";
        }
    }

    protected Location getRandomLocation() throws InvalidPegLocationException {
        String randomColumn = Column.getRandomColumn().name();
        int rowIndex = new Random().nextInt((10 - 1) + 1) + 1;

        return new Location(randomColumn + rowIndex);
    }

    public Grid getMyBoard() { return myBoard; }

    public Grid getOpponentsBoard() { return opponentsBoard; }
}
