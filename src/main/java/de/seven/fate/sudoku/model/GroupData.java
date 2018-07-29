package de.seven.fate.sudoku.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class GroupData {

    @NotNull
    private final PositionData positionData;

    @Size(max = 9)
    private final List<CellData> cells = new ArrayList<>();

    public GroupData(PositionData positionData) {
        this.positionData = positionData;
    }

    public PositionData getPositionData() {
        return positionData;
    }

    public List<CellData> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("positionData", positionData)
                .append("CellData", cells.size())
                .toString();
    }
}
