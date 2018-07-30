package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.exceptions.GameUnresolvableException;
import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.model.GroupData;
import de.seven.fate.sudoku.repository.GameRepository;
import de.seven.fate.sudoku.validator.GameValidator;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractGameService implements GameService {

    private final GameRepository gameRepository;
    private final GameValidator gameValidator;
    private GameData gameData;

    public AbstractGameService(GameRepository gameRepository, GameValidator gameValidator) {
        this.gameRepository = gameRepository;
        this.gameValidator = gameValidator;
    }

    @Override
    public GameData start(GameLevel gameLevel) {
        Validate.notNull(gameLevel);

        gameData = gameRepository.create();

        List<Integer> values = getAvailableValues();

        initializeValues(values);

        return gameData;
    }

    @Override
    public void nextStep(GameData gameData) {

        if (!gameRepository.solve(gameData)) {

            throw new GameUnresolvableException("This game can't be solved!");
        }
    }

    @Override
    public void setNextStep(GameData gameData, CellData cellData) {

        gameValidator.validateValue(cellData.getValue(), gameData);

        CellData target = gameRepository.getCellData(cellData.getPositionData(), gameData);

        gameRepository.setValue(cellData.getValue(), target);
    }

    @Override
    public boolean isDone(GameData gameData) {

        return gameRepository.isDone(gameData);
    }

    @Override
    public boolean solve(GameData gameData) {

        gameRepository.solve(gameData);

        return isDone(gameData);
    }

    private void initializeValues(List<Integer> values) {
        assert values != null;

        for (GroupData groupData : gameData.getGroups()) {

            if (values.isEmpty()) {
                return;
            }

            List<CellData> cells = groupData.getCells().stream().filter(it -> it.getValue() == 0).collect(Collectors.toList());

            if (cells.isEmpty()) {
                return;
            }

            Collections.shuffle(cells);
            Collections.shuffle(values);

            Integer value = values.remove(0);

            CellData cellData = cells.get(0);

            if (gameValidator.isValidValue(cellData, value)) {

                cellData.setValue(value);
            } else {

                initializeValues(values);
            }
        }
    }

    private List<Integer> getAvailableValues() {

        return IntStream.range(gameData.getMinValueSize(), gameData.getMaxValueSize()).boxed().collect(Collectors.toList());
    }
}
