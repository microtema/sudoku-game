package de.seven.fate.sudoku.exceptions;

/**
 * GameRepository Unresolvable Exception
 */
public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String message) {
        super(message);
    }
}
