package com.zuhlke.cs.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class CanvasApplicationTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void drawLineThrowsExceptionForIllegalArgument() {
        CanvasApplication app = new CanvasApplication();
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid number of arguments");

        app.parseInput("C 20 4");
        app.parseInput("L 1 2 3");
    }

    @Test
    public void drawLineThrowsExceptionIfNaN() {
        CanvasApplication app = new CanvasApplication();
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number expected!");

        app.parseInput("C 20 4");
        app.parseInput("L 1 2 3 w");
    }

    @Test
    public void unknownCommandThrowsException() {
        CanvasApplication app = new CanvasApplication();
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown command: 'F'");

        app.parseInput("C 20 4");
        app.parseInput("F 1 1");
    }

    @Test
    public void drawLine() {
        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        CanvasApplication app = new CanvasApplication();
        app.parseInput("C 20 4");
        app.parseInput("L 1 2 6 2");

        assertEquals(expected, app.getCanvas().render());
    }

    @Test
    public void drawLineUndo() {
        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        CanvasApplication app = new CanvasApplication();
        app.parseInput("C 20 4");
        app.parseInput("L 1 2 6 2");
        assertEquals(expected, app.getCanvas().render());

        app.parseInput("U");

        String expectedAfterUndo =
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(expectedAfterUndo, app.getCanvas().render());
    }

    @Test
    public void drawLineUndoAfterRedo() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n";

        CanvasApplication app = new CanvasApplication();
        app.parseInput("C 20 4");
        app.parseInput("L 1 2 6 2");
        assertEquals(expected, app.getCanvas().render());

        app.parseInput("U");

        String expectedAfterUndo =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n";

        assertEquals(expectedAfterUndo, app.getCanvas().render());

        app.parseInput("E");

        assertEquals(expected, app.getCanvas().render());

        app.parseInput("u");

        assertEquals(expectedAfterUndo, app.getCanvas().render());
    }

    @Test
    public void drawRectangle() {
        String expected =
                "----------------------\n" +
                "| xxx                |\n" +
                "| x x                |\n" +
                "| xxx                |\n" +
                "|                    |\n" +
                "----------------------\n";

        CanvasApplication app = new CanvasApplication();
        app.parseInput("C 20 4");
        app.parseInput("R 2 1 4 3");

        assertEquals(expected, app.getCanvas().render());
    }

    @Test
    public void fill() {
        String expected =
                "----------------------\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "----------------------\n";

        CanvasApplication app = new CanvasApplication();
        app.parseInput("C 20 6");
        app.parseInput("L 3 1 3 6");
        app.parseInput("L 1 3 20 3");
        app.parseInput("B 5 2 y");

        assertEquals(expected, app.getCanvas().render());
    }

    @Test
    public void closeTheApplication() {
        CanvasApplication app = new CanvasApplication();
        app.run(new Scanner("Q"));
    }
}
