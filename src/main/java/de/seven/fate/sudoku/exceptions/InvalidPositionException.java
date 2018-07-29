package de.seven.fate.sudoku.exceptions;

/**
 * Game Unresolvable Exception
 */
public class InvalidPositionException extends RuntimeException {

    public InvalidPositionException(String message) {
        super(message);
    }
}
