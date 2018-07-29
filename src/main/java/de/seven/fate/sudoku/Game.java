package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.model.PositionData;

public interface Game {

    void start(GameLevel gameLevel);

    void nextStep();

    boolean isDone();

    void setNexValue(int value, PositionData positionData);
}
