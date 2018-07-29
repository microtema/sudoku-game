package de.seven.fate.sudoku;

import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.model.RowData;
import org.apache.commons.lang3.Validate;

public class ConsoleGamePrinter implements GamePrinter {

    private final static char[] CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J'};

    @Override
    public void print(GameData gameData) {
        Validate.notNull(gameData);

        System.out.println();
        System.out.println("    1   2   3   4   5   6   7   8   9");
        System.out.println("   ___ ___ ___ ___ ___ ___ ___ ___ ___");

        int rowIndex = 0;

        for (RowData rowData : gameData.getRows()) {

            System.out.print(CHARS[rowIndex++] + " |");

            for (CellData cellData : rowData.getCells()) {

                int value = cellData.getValue();

                System.out.print(value == 0 ? "___|" : "_" + (value) + "_|");
            }

            System.out.println();
        }

        System.out.println();
    }
}
