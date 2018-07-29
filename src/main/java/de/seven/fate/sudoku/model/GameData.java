package de.seven.fate.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    private final int maxRows = 9;
    private final int maxColumns = 9;
    private final int minValueSize = 1;
    private final int maxValueSize = 10;

    private final List<RowData> rows = new ArrayList<>();
    private final List<ColumnData> columns = new ArrayList<>();
    private final List<GroupData> groups = new ArrayList<>();

    public List<RowData> getRows() {
        return rows;
    }

    public List<GroupData> getGroups() {
        return groups;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public List<ColumnData> getColumns() {
        return columns;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public int getMinValueSize() {
        return minValueSize;
    }

    public int getMaxValueSize() {
        return maxValueSize;
    }
}
