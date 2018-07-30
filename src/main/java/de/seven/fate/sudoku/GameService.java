package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;

/**
 * GameRepository interface
 */
public interface GameService {

    /**
     * Create new game by given gameLevel
     *
     * @param gameLevel may not be null
     */
    GameData start(GameLevel gameLevel);

    /**
     * Solve next step (automated)
     * <p>
     * throws GameUnresolvableException
     *
     * @param gameData may not be null
     */
    void nextStep(GameData gameData);

    /**
     * Solve next step (custom)
     * <p>
     * throws GameUnresolvableException
     *
     * @param cellData may not be null
     * @param gameData     may not be null
     */
    void setNextStep(GameData gameData, CellData cellData);

    /**
     * Retur true if game is complete resolved else false
     * @param gameData may not be null
     * @return boolean
     */
    boolean isDone(GameData gameData);

    /**
     * Solve the game if doable
     *
     * @param gameData may not be null
     */
    boolean solve(GameData gameData);
}
