package de.seven.fate.sudoku.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CellData is the small unit with relations to Row-, Column- and Group-Data
 *
 * CellData has unique position
 */
public class CellData {

    @NotNull
    private final PositionData positionData;

    @NotNull
    private final RowData rowData;

    @NotNull
    private final ColumnData columnData;

    @NotNull
    private final GroupData groupData;

    @Size(min = 0, max = 9)
    private int value;


    public CellData(PositionData positionData, RowData rowData, ColumnData columnData, GroupData groupData) {
        this.positionData = positionData;
        this.rowData = rowData;
        this.columnData = columnData;
        this.groupData = groupData;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public RowData getRowData() {
        return rowData;
    }

    public GroupData getGroupData() {
        return groupData;
    }

    public PositionData getPositionData() {
        return positionData;
    }


    public ColumnData getColumnData() {
        return columnData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CellData)) return false;

        CellData cellData = (CellData) o;

        return new EqualsBuilder()
                .append(value, cellData.value)
                .append(positionData, cellData.positionData)
                .append(rowData, cellData.rowData)
                .append(columnData, cellData.columnData)
                .append(groupData, cellData.groupData)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(positionData)
                .append(rowData.getIndex())
                .append(columnData.getIndex())
                .append(value)
                .append(groupData.getPositionData())
                .toHashCode();
    }
}
