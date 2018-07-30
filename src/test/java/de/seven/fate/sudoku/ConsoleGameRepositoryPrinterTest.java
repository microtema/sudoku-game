package de.seven.fate.sudoku;

import de.seven.fate.sudoku.model.GameData;
import de.seven.fate.sudoku.repository.GameRepository;
import de.seven.fate.sudoku.validator.GameValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConsoleGameRepositoryPrinterTest {

    ConsoleGamePrinter sut = new ConsoleGamePrinter();

    GameRepository gameRepository = new GameRepository(new GameValidator());

    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);

    @Before
    public void setUp() throws Exception {

        // Tell Java to use your special stream
        System.setOut(ps);
    }

    @After
    public void tearDown(){

        // Put things back
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void printBlank() {

        sut.print(gameRepository.create());

        assertEquals("\n" +
                "    1   2   3   4   5   6   7   8   9\n" +
                "   ___ ___ ___ ___ ___ ___ ___ ___ ___\n" +
                "A |___|___|___|___|___|___|___|___|___|\n" +
                "B |___|___|___|___|___|___|___|___|___|\n" +
                "C |___|___|___|___|___|___|___|___|___|\n" +
                "D |___|___|___|___|___|___|___|___|___|\n" +
                "E |___|___|___|___|___|___|___|___|___|\n" +
                "F |___|___|___|___|___|___|___|___|___|\n" +
                "G |___|___|___|___|___|___|___|___|___|\n" +
                "H |___|___|___|___|___|___|___|___|___|\n" +
                "J |___|___|___|___|___|___|___|___|___|\n" +
                "\n", baos.toString());
    }

    @Test
    public void printWithValues() {

        GameData gameData = gameRepository.create();

        gameData.getRows().get(0).getCells().get(0).setValue(1);
        gameData.getRows().get(1).getCells().get(1).setValue(2);
        gameData.getRows().get(2).getCells().get(2).setValue(3);
        gameData.getRows().get(3).getCells().get(3).setValue(4);
        gameData.getRows().get(4).getCells().get(4).setValue(5);
        gameData.getRows().get(5).getCells().get(5).setValue(6);
        gameData.getRows().get(6).getCells().get(6).setValue(7);
        gameData.getRows().get(7).getCells().get(7).setValue(8);
        gameData.getRows().get(8).getCells().get(8).setValue(9);

        sut.print(gameData);

        assertEquals("\n" +
                "    1   2   3   4   5   6   7   8   9\n" +
                "   ___ ___ ___ ___ ___ ___ ___ ___ ___\n" +
                "A |_1_|___|___|___|___|___|___|___|___|\n" +
                "B |___|_2_|___|___|___|___|___|___|___|\n" +
                "C |___|___|_3_|___|___|___|___|___|___|\n" +
                "D |___|___|___|_4_|___|___|___|___|___|\n" +
                "E |___|___|___|___|_5_|___|___|___|___|\n" +
                "F |___|___|___|___|___|_6_|___|___|___|\n" +
                "G |___|___|___|___|___|___|_7_|___|___|\n" +
                "H |___|___|___|___|___|___|___|_8_|___|\n" +
                "J |___|___|___|___|___|___|___|___|_9_|\n" +
                "\n", baos.toString());
    }
}