package de.seven.fate.sudoku.validator;

import de.seven.fate.sudoku.exceptions.InvalidValueException;
import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;
import org.apache.commons.lang3.Validate;

public class GameValidator {

    public void validateValue(int value, GameData gameData) {

        if (value < gameData.getMinValueSize()) {

            throw new InvalidValueException("Invalid value. Must be greater then: " + (gameData.getMinValueSize() - 1));
        }

        if (value > gameData.getMaxValueSize()) {

            throw new InvalidValueException("Invalid value. Must be less then: " + (gameData.getMaxValueSize() + 1));
        }
    }

    public boolean isValidValue(CellData cellData, int value) {
        Validate.notNull(cellData);

        // Is value used in row.
        boolean anyMatch = cellData.getRowData().getCells().stream().anyMatch(it -> it.getValue() == value);

        if (anyMatch) {
            return false;
        }

        // Is value used in column.
        anyMatch = cellData.getColumnData().getCells().stream().anyMatch(it -> it.getValue() == value);
        if (anyMatch) {
            return false;
        }

        // Is value used in group.
        return cellData.getGroupData().getCells().stream().noneMatch(it -> it.getValue() == value);
    }

    public boolean isFreeCellData(CellData cellData) {
        return cellData.getValue() == 0;
    }
}
