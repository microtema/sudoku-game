package de.seven.fate.sudoku.enums;

public enum  GameLevel {

    EASY(4),
    MEDIUM(2),
    PROFI(1);

    private final int level;

    private GameLevel(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
