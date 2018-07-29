# sudoku-game

> Key information:
> Each Cell has one-to-one relation to row, column and block
> Each Row contains a range or values (1..9)
> Each Column contains a range or values (1..9)
> Each Block (3x3) contains a range or values (1..9)
> Each Cell has unique value of values (1..9) cross rows, columns and group where it belongs
> Before set the value of range (1..9) to cell/field, check whenever this value is already available in row, column or block.
> The Game is marked as unresolved when there is no more possible to set the unique value to cell.
> \* Order of values is not important

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

Sudoku Machine Game

Simple CLI based Sudoku Game

Commands:
* set \<value\> \<Field-Letter\> \<Field-Index\>
* solve

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
    
