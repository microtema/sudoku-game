package de.seven.fate.sudoku.repository;

import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.ColumnData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.model.GroupData;
import de.seven.fate.sudoku.model.PositionData;
import de.seven.fate.sudoku.model.RowData;
import de.seven.fate.sudoku.validator.GameValidator;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class GameServiceRepositoryTest {

    GameRepository sut = new GameRepository(new GameValidator());

    @Test
    public void create() {

        assertNotNull(sut.create());
    }

    @Test
    public void groups() {

        GameData gameData = sut.create();

        List<GroupData> groups = gameData.getGroups();

        assertEquals(9, groups.size());

        GroupData data = groups.get(0);

        PositionData positionData = data.getPositionData();

        assertEquals(0, positionData.getRowIndex());
        assertEquals(0, positionData.getColumnIndex());

        data = groups.get(2);

        positionData = data.getPositionData();

        assertEquals(0, positionData.getRowIndex());
        assertEquals(2, positionData.getColumnIndex());

        data = groups.get(4);

        positionData = data.getPositionData();

        assertEquals(1, positionData.getRowIndex());
        assertEquals(1, positionData.getColumnIndex());

        data = groups.get(8);

        positionData = data.getPositionData();

        assertEquals(2, positionData.getRowIndex());
        assertEquals(2, positionData.getColumnIndex());

        for (GroupData groupData : groups) {

            List<CellData> cells = groupData.getCells();
            assertEquals(9, cells.size());
            cells.forEach(it -> assertSame(groupData, it.getGroupData()));
        }
    }

    @Test
    public void rows() {

        GameData gameData = sut.create();

        List<RowData> rows = gameData.getRows();

        assertEquals(9, rows.size());

        for (RowData rowData : rows) {

            List<CellData> cells = rowData.getCells();
            assertEquals(9, cells.size());
            cells.forEach(it -> assertSame(rowData, it.getRowData()));
        }
    }

    @Test
    public void columns() {

        GameData gameData = sut.create();

        List<ColumnData> columns = gameData.getColumns();

        assertEquals(9, columns.size());

        for (ColumnData columnData : columns) {

            List<CellData> cells = columnData.getCells();
            assertEquals(9, cells.size());
            cells.forEach(it -> assertSame(columnData, it.getColumnData()));
        }
    }

    @Test
    public void getRelatedRowDataAndGroupDataOnFirstCellData() {

        GameData gameData = sut.create();

        List<RowData> rows = gameData.getRows();
        List<GroupData> groups = gameData.getGroups();

        RowData rowData = rows.get(0);

        List<CellData> cells = rowData.getCells();

        CellData cellData = cells.get(0);

        assertSame(rowData, cellData.getRowData());
        assertSame(0, cellData.getPositionData().getRowIndex());
        assertSame(0, cellData.getPositionData().getColumnIndex());

        GroupData groupData = groups.get(0);

        assertSame(groupData, cellData.getGroupData());
    }

    @Test
    public void getRelatedRowDataAndGroupDataOnThirdCellData() {

        GameData gameData = sut.create();

        List<RowData> rows = gameData.getRows();
        List<GroupData> groups = gameData.getGroups();

        RowData rowData = rows.get(0);

        List<CellData> cells = rowData.getCells();

        CellData cellData = cells.get(2);

        assertSame(rowData, cellData.getRowData());
        assertSame(0, cellData.getPositionData().getRowIndex());
        assertSame(2, cellData.getPositionData().getColumnIndex());

        GroupData groupData = groups.get(0);

        assertSame(groupData, cellData.getGroupData());
    }

    @Test
    public void getRelatedRowDataAndGroupDataOnLastCellData() {

        GameData gameData = sut.create();

        List<RowData> rows = gameData.getRows();
        List<GroupData> groups = gameData.getGroups();

        RowData rowData = rows.get(8);

        List<CellData> cells = rowData.getCells();

        CellData cellData = cells.get(8);

        assertSame(rowData, cellData.getRowData());
        assertSame(8, cellData.getPositionData().getRowIndex());
        assertSame(8, cellData.getPositionData().getColumnIndex());

        GroupData groupData = groups.get(8);

        assertSame(groupData, cellData.getGroupData());
    }


    @Test
    public void getCells() {

        GameData gameData = sut.create();

        List<CellData> cells = sut.getCells(gameData);

        assertEquals(81, cells.size());

        Set<CellData> set = new HashSet<>(cells);

        assertEquals(cells.size(), set.size());
    }

}