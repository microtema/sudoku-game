package de.seven.fate.sudoku.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Size;

public class PositionData {

    @Size(min = 0, max = 9)
    private final int rowIndex;

    @Size(min = 0, max = 9)
    private final int columnIndex;

    public PositionData(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PositionData)) return false;

        PositionData that = (PositionData) o;

        return new EqualsBuilder()
                .append(rowIndex, that.rowIndex)
                .append(columnIndex, that.columnIndex)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(rowIndex)
                .append(columnIndex)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("rowIndex", rowIndex)
                .append("columnIndex", columnIndex)
                .toString();
    }
}
