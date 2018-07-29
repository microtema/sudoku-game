package de.seven.fate.sudoku.exceptions;

/**
 * Game Unresolvable Exception
 */
public class GameUnresolvableException extends RuntimeException {

    public GameUnresolvableException(String message) {
        super(message);
    }
}
