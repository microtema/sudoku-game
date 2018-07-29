package de.seven.fate.sudoku.service;

import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.ColumnData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.model.GroupData;
import de.seven.fate.sudoku.model.PositionData;
import de.seven.fate.sudoku.model.RowData;
import org.apache.commons.lang3.Validate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameService {

    /**
     * @return new Game
     */
    public GameData create() {

        GameData gameData = new GameData();

        List<RowData> rows = gameData.getRows();
        List<ColumnData> columns = gameData.getColumns();
        List<GroupData> groups = gameData.getGroups();

        GroupData groupData;
        ColumnData columnData;
        Map<Integer, ColumnData> columnDataMap = new HashMap<>();
        Map<PositionData, GroupData> positionDataGroupDataMap = new HashMap<>();

        //Initialize rows
        for (int rowIndex = 0; rowIndex < gameData.getMaxRows(); rowIndex++) {

            //create new rowData
            RowData rowData = new RowData(rowIndex);

            rows.add(rowData);

            //initialize cells for this rowData
            for (int columnIndex = 0; columnIndex < gameData.getMaxColumns(); columnIndex++) {

                //create column-data on on first row only
                columnData = columnDataMap.computeIfAbsent(columnIndex, index -> {
                    ColumnData data = new ColumnData(index);
                    columns.add(data);
                    return data;
                });

                //create new group-data every third column/row index only
                groupData = positionDataGroupDataMap.computeIfAbsent(new PositionData(rowIndex / 3, columnIndex / 3), positionData -> {
                    GroupData data = new GroupData(positionData);
                    groups.add(data);
                    return data;
                });

                PositionData positionData = new PositionData(rowIndex, columnIndex);

                CellData cellData = new CellData(positionData, rowData, columnData, groupData);

                rowData.getCells().add(cellData);
                columnData.getCells().add(cellData);
                groupData.getCells().add(cellData);
            }
        }

        return gameData;
    }

    public List<CellData> getCells(GameData gameData) {
        Validate.notNull(gameData);

        return gameData.getRows().stream()
                .map(RowData::getCells)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Find CellData that is free to set a value
     *
     * @param gameData may not be null
     * @return CellData or null
     */
    public CellData findNextFreeCell(GameData gameData) {
        Validate.notNull(gameData);

        return getCells(gameData)
                .stream()
                .filter(this::isFreeCellData)
                .findFirst().orElse(null);
    }

    /**
     * Find CellData by position
     *
     * @param gameData     may not be null
     * @param positionData may n ot be null
     * @return CellData or null
     */
    public CellData findCellByPosition(GameData gameData, PositionData positionData) {
        Validate.notNull(gameData);
        Validate.notNull(positionData);

        return getCells(gameData).stream()
                .filter(it -> Objects.equals(it.getPositionData(), positionData))
                .findAny()
                .orElse(null);
    }

    public boolean isDone(GameData gameData) {

        return getCells(gameData).stream().noneMatch(this::isFreeCellData);
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

    private boolean isFreeCellData(CellData cellData) {
        assert cellData != null;

        return cellData.getValue() == 0;
    }


}
