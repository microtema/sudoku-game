# sudoku-game
Sudoku Machine Game

Data Model

* CellData
     * position: PositionData (final)
     * value: int
     * rowData: RowData (final)
     * columnData: ColumnData (final)
* RowData
     * index: int (final) //row index in game field
     * List < CellData > cells //Set of cells (9)
* ColumnData
     * index: int (final) //column index
     * List < CellData > cells //Set of cells (9)
* GroupData
     * position: PositionData (final)
     * List < CellData > cells //Set of cells (9)
* GameData
     * List < RowData > rows //Set of rows (9)
     * List < ColumnData > column //Set of column (9)
     * List < GroupData > groups //Set of groups (9)
* PositionData
     * rowIndex: int
     * columnIndex: int
     
Interface API

* Game
    * create(GameLevel gameLevel) // create new game depending of gameLevel
    * nextStep() // solve next step (automated)
    * nextStep(value:int, position:PositionData) // solve next step (custom)
    * isDone() //determine whenever the game is done

Simple CLI based Sudoku Game

Commands:
* set \<value\> \<Field-Letter\> \<Field-Index\>
* solve

> Key information:
> Each Cell has relation to row, column and block
> If any value of range (1..9) is already available in row, column or block, 
> the Game is marked as unresolved (GAME-OVER).

## Technology Stack

* Java 1.8
    * Streams 
    * Lambdas
* Third Party Libraries
    * Commons-Lang3 (Apache License)
    * Junit (EPL 1.0 License)
* Build Tool
    * Gradle (Apache License)
    
## License

MIT (unless noted otherwise)
    
