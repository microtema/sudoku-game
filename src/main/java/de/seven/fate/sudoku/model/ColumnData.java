package de.seven.fate.sudoku.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ColumnData {

    private final int index;

    private final List<CellData> cells = new ArrayList<>();

    public ColumnData(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public List<CellData> getCells() {
        return cells;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ColumnData)) return false;

        ColumnData rowData = (ColumnData) o;

        return new EqualsBuilder()
                .append(index, rowData.index)
                .append(cells, rowData.cells)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(index)
                .append(cells)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("index", index)
                .append("cells", cells)
                .toString();
    }
}
