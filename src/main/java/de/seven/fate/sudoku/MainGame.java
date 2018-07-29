package de.seven.fate.sudoku;

import de.seven.fate.sudoku.enums.GameLevel;
import de.seven.fate.sudoku.model.PositionData;
import de.seven.fate.sudoku.service.GameService;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class MainGame {

    private static boolean running = true;

    private static Game game = new MachineGame(new ConsoleGamePrinter(), new GameService());

    static {

        System.out.println("Start new Game....");

        game.start(GameLevel.PROFI);

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

                game.nextStep();

                running = false;
            } else if (command.startsWith("set")) {

                String[] commands = command.split("\\s+");

                int value = Integer.parseInt(commands[1]);
                int rowIndex = Character.getNumericValue(commands[2].charAt(0)) - 10;
                int columnIndex = Integer.parseInt(commands[3]) -1;

                game.setNextStep(value, new PositionData(rowIndex, columnIndex));
            }

        }
    }

}
