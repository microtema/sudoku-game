package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.model.CellData;
import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.model.PositionData;
import de.seven.fate.sudoku.repository.GameRepository;
import de.seven.fate.sudoku.validator.GameValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class MainGame {

    private static boolean running = true;

    private static GameService gameService = new MachineGameService(new ConsoleGamePrinter(), new GameRepository(new GameValidator()), new GameValidator());

    private static GameData gameData;

    static {

        System.out.println("Start new GameRepository....");

        gameData = gameService.start(GameLevel.PROFI);

        System.out.println();
        System.out.println("Commands:");
        System.out.println("> set <value> <Field Letter> <Field-Index>");
        System.out.println("> solve");
        System.out.println();
    }


    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (running) {

            String command = scanner.nextLine();

            if (StringUtils.isEmpty(command)) {

                System.out.println("Please enter a command listed above.");

            } else if (command.equals("solve")) {

                gameService.nextStep(gameData);

                running = false;
            } else if (command.startsWith("set")) {

                String[] commands = command.split("\\s+");

                int value = Integer.parseInt(commands[1]);
                int rowIndex = Character.getNumericValue(commands[2].charAt(0)) - 10;
                int columnIndex = Integer.parseInt(commands[3]) - 1;

                CellData cellData = new CellData(new PositionData(rowIndex, columnIndex), null, null, null);

                cellData.setValue(value);

                gameService.setNextStep(gameData, cellData);
            }

        }
    }

}
