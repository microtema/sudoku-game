package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
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

        List<Integer> values = IntStream.range(gameData.getMinValueSize(), gameData.getMaxValueSize()).boxed().collect(Collectors.toList());

        initializeValues(values);

        System.out.println("Start new Game....");

        consoleGamePrinter.print(gameData);
    }

    @Override
    public void nextStep() {

        if (!resolveImpl()) {
            System.out.println("This game can't be solved!");
        }

        consoleGamePrinter.print(gameData);
    }

    @Override
    public boolean isDone() {

        return gameService.isDone(gameData);
    }

    @Override
    public void setNexValue(int value, PositionData positionData) {

        if (value < gameData.getMinValueSize()) {

            System.err.println("Invalid value. Must be greater then: " + (gameData.getMinValueSize() - 1));
            return;
        }

        if (value > gameData.getMaxValueSize()) {

            System.err.println("Invalid value. Must be less then: " + (gameData.getMaxValueSize() + 1));
            return;
        }

        CellData cellData = gameService.findCellByPosition(gameData, positionData);

        if (cellData == null) {

            System.err.println("Unable to find field!");

            return;
        }

        boolean validValue = gameService.isValidValue(cellData, value);

        if (!validValue) {
            System.err.println(value + " is invalid Value for this field. Please try again.");
        }

        cellData.setValue(value);

        consoleGamePrinter.print(gameData);
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


}
