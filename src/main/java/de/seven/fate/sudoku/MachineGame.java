package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.exceptions.GameUnresolvableException;
import de.seven.fate.sudoku.exceptions.InvalidValueException;
import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.model.GroupData;
import de.seven.fate.sudoku.model.PositionData;
import de.seven.fate.sudoku.service.GameService;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MachineGame implements Game {

    private final ConsoleGamePrinter consoleGamePrinter;
    private final GameService gameService;
    private GameData gameData;

    public MachineGame(ConsoleGamePrinter consoleGamePrinter, GameService gameService) {
        this.consoleGamePrinter = consoleGamePrinter;
        this.gameService = gameService;
    }

    @Override
    public void start(GameLevel gameLevel) {
        Validate.notNull(gameLevel);

        gameData = gameService.create();

        List<Integer> values = getAvailableValues();

        initializeValues(values);

        consoleGamePrinter.print(gameData);
    }

    @Override
    public void nextStep() {

        if (!resolveImpl()) {

            throw new GameUnresolvableException("This game can't be solved!");
        }

        consoleGamePrinter.print(gameData);
    }

    @Override
    public void setNextStep(int value, PositionData positionData) {

        validateValue(value);

        CellData cellData = getCellData(positionData);

        setValue(value, cellData);

        consoleGamePrinter.print(gameData);
    }

    @Override
    public boolean isDone() {

        return gameService.isDone(gameData);
    }

    private void setValue(int value, CellData cellData) {
        assert cellData != null;

        boolean validValue = gameService.isValidValue(cellData, value);

        if (!validValue) {

            throw new InvalidValueException(value + " is invalid Value for this field. Please try again.");
        }

        cellData.setValue(value);
    }

    private CellData getCellData(PositionData positionData) {

        CellData cellData = gameService.findCellByPosition(gameData, positionData);

        if (cellData == null) {

            throw new InvalidValueException("Unable to find field!");
        }
        return cellData;
    }

    private void validateValue(int value) {

        if (value < gameData.getMinValueSize()) {

            throw new InvalidValueException("Invalid value. Must be greater then: " + (gameData.getMinValueSize() - 1));
        }

        if (value > gameData.getMaxValueSize()) {

            throw new InvalidValueException("Invalid value. Must be less then: " + (gameData.getMaxValueSize() + 1));
        }
    }

    private void initializeValues(List<Integer> values) {
        assert values != null;

        for (GroupData groupData : gameData.getGroups()) {

            if (values.isEmpty()) {
                return;
            }

            List<CellData> cells = groupData.getCells().stream().filter(it -> it.getValue() == 0).collect(Collectors.toList());

            if (cells.isEmpty()) {
                return;
            }

            Collections.shuffle(cells);
            Collections.shuffle(values);

            Integer value = values.remove(0);

            CellData cellData = cells.get(0);

            if (gameService.isValidValue(cellData, value)) {

                cellData.setValue(value);
            } else {

                initializeValues(values);
            }
        }
    }

    private boolean resolveImpl() {

        CellData freeCell = gameService.findNextFreeCell(gameData);

        // There is not more free cellData.
        if (freeCell == null) {
            return true;
        }

        for (int value = gameData.getMinValueSize(); value < gameData.getMaxValueSize(); value++) {

            if (!gameService.isValidValue(freeCell, value)) {
                continue;
            }

            freeCell.setValue(value);

            if (resolveImpl()) {

                return true; //Game is done
            } else {

                //NOTE: reset the value again
                freeCell.setValue(0);
            }
        }

        return false;
    }

    private List<Integer> getAvailableValues() {

        return IntStream.range(gameData.getMinValueSize(), gameData.getMaxValueSize()).boxed().collect(Collectors.toList());
    }
}
