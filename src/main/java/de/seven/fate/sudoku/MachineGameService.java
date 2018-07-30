package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.repository.GameRepository;
import de.seven.fate.sudoku.validator.GameValidator;

public class MachineGameService extends AbstractGameService {

    private final ConsoleGamePrinter consoleGamePrinter;
    private GameData gameData;

    public MachineGameService(ConsoleGamePrinter consoleGamePrinter, GameRepository gameRepository, GameValidator gameValidator) {
        super(gameRepository, gameValidator);
        this.consoleGamePrinter = consoleGamePrinter;
    }

    @Override
    public GameData start(GameLevel gameLevel) {

        gameData = super.start(gameLevel);

        consoleGamePrinter.print(gameData);

        return gameData;
    }

    @Override
    public void nextStep(GameData gameData) {

        super.nextStep(gameData);

        consoleGamePrinter.print(gameData);
    }

    @Override
    public void setNextStep(GameData gameData, CellData cellData) {

        super.setNextStep(gameData, cellData);

        consoleGamePrinter.print(gameData);
    }
}
