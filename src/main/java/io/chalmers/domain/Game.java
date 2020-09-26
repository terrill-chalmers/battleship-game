package io.chalmers.domain;

import io.chalmers.exception.InvalidPegLocationException;
import io.chalmers.exception.InvalidShipLocationException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class Game {
    private Grid myBoard;
    private Grid opponentsBoard;

    public Game() {
        myBoard = new Grid();
        opponentsBoard = new Grid();
    }

    public void playGame() {
        setShips(myBoard);

        takeTurns();

        //should game over be class capable of knowing when game is over, who won, and printing congrats, number of turns, etc.

    }

    protected void takeTurns() {
        boolean gameOver = false;
        do {
            for(int turn = 0; turn < 2; turn++) {
                if(turn == 0) {
                    System.out.println(enterGuess());
                    gameOver = checkForGameOver();
                } else if(!gameOver) {
                    guess(myBoard, getRandomLocation());
                    gameOver = checkForGameOver();
                }
            }
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
            MyScanner scanner = getMyScanner();
            String startingLocation = scanner.askForInput("Enter starting location for " + shipType.getName());
            String direction = scanner.askForInput("Enter direction for " + shipType.getName() + " R(for Right) or D" +
                    "(for Down)");

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

            markShipPegsAsOccupied(board, shipType.getSize(), checkPegLocations(board, shipType, direction, start));
            locationOk = true;

        } catch (InvalidPegLocationException | InvalidShipLocationException | ArrayIndexOutOfBoundsException ex) {
            locationOk = false;
        }

        return locationOk;
    }

    private List<Peg> checkPegLocations(Grid board, ShipType shipType, String direction, Location start) {
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
        return proposedPegs;
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
            MyScanner scanner = getMyScanner();
            Location location = new Location(scanner.askForInput("Guess location"));

            return guess(opponentsBoard, location);

        } catch (InvalidPegLocationException e) {
            return "Invalid guess.";
        }
    }

    protected MyScanner getMyScanner() {
        return new MyScanner();
    }

    protected Location getRandomLocation() {
        boolean validLocation;
        Location randomLocation = null;

        do {
            String randomColumn = Column.getRandomColumn().name();
            int rowIndex = new Random().nextInt((10 - 1) + 1) + 1;

            try {
                randomLocation = new Location(randomColumn + rowIndex);
                validLocation = true;
            } catch (InvalidPegLocationException e) {
                validLocation = false;
            }
        } while (!validLocation);

        return randomLocation;
    }

    public Grid getMyBoard() { return myBoard; }

    public Grid getOpponentsBoard() { return opponentsBoard; }
}
