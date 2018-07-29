package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.model.PositionData;

/**
 * Game interface
 */
public interface Game {

    /**
     * Create new game by given gameLevel
     *
     * @param gameLevel may not be null
     */
    void start(GameLevel gameLevel);

    /**
     * Solve next step (automated)
     *
     * throws GameUnresolvableException
     */
    void nextStep();

    /**
     * Solve next step (custom)
     * <p>
     * throws GameUnresolvableException
     *
     * @param value        may be zero
     * @param positionData may not be null
     */
    void setNextStep(int value, PositionData positionData);

    /**
     * Retur true if game is complete resolved else false
     * @return boolean
     */
    boolean isDone();
}
