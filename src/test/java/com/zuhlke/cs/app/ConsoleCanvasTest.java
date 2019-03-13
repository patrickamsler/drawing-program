package com.zuhlke.cs.app;

import com.zuhlke.cs.model.Line;
import com.zuhlke.cs.model.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleCanvasTest {

    @Test
    public void newCanvasCreatesEmptyDrawingAreaWithBorder() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        String actual = canvas.render();

        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void newCanvasSmallerThan2ThrowsException() {
        new ConsoleCanvas(1, 1);
    }

    @Test
    public void drawHorizontalLine() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(1, 2, 6, 2);
        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();

        assertEquals(expected, actual);
    }

    @Test
    public void drawHorizontalLineUndo() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(1, 2, 6, 2);
        String expected =
            "----------------------\n" +
            "|                    |\n" +
            "|xxxxxx              |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();
        assertEquals(expected, actual);

        canvas.undo();
        String actualUndo = canvas.render();

        String expectedAfterUndo =
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(expectedAfterUndo, actualUndo);
    }

    @Test
    public void drawHorizontalLineRedo() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(1, 2, 6, 2);
        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();
        assertEquals(expected, actual);

        canvas.undo();
        String actualUndo = canvas.render();

        String expectedAfterUndo =
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(expectedAfterUndo, actualUndo);

        canvas.redo();
        String actualRedo = canvas.render();
        assertEquals(expected, actualRedo);
    }

    @Test
    public void drawHorizontalLineAfterRedo() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(1, 2, 6, 2);
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();
        assertEquals(expected, actual);

        canvas.undo();
        String actualUndo = canvas.render();

        String expectedAfterUndo =
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(expectedAfterUndo, actualUndo);

        Line line2 = new Line(1, 2, 4, 2);
        canvas.drawShape(line2);

        String expected2 =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxx                |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        String actual2 = canvas.render();
        assertEquals(expected2, actual2);

        canvas.redo();
        String actualRedo = canvas.render();
        assertEquals(expected2, actualRedo);
    }

    @Test
    public void redoAfterUndo() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(1, 2, 6, 2);
        String expectedStart =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();
        assertEquals(expectedStart, actual);

        canvas.undo();
        String actualUndo = canvas.render();

        String expectedAfterUndo =
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(expectedAfterUndo, actualUndo);

        canvas.redo();
        assertEquals(expectedStart, canvas.render());

        canvas.redo();
        assertEquals(expectedStart, canvas.render());

        canvas.undo();
        assertEquals(expectedAfterUndo, canvas.render());
    }

    @Test
    public void drawVerticalLine() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(2, 1, 2, 4);
        String expected =
                "----------------------\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();

        assertEquals(expected, actual);
    }

    @Test
    public void drawLineLongerThanCanvas() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(2, 0, 2, 42);
        String expected =
                "----------------------\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "----------------------\n";

        canvas.drawShape(line);
        String actual = canvas.render();

        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void linesOutOfBoundsCannotBeDrawn() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Line line = new Line(1, 5, 4, 5);

        canvas.drawShape(line);
    }

    @Test
    public void drawRectangle() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 4);
        Rectangle rectangle = new Rectangle(2,1,4,3);
        String expected =
                "----------------------\n" +
                "| xxx                |\n" +
                "| x x                |\n" +
                "| xxx                |\n" +
                "|                    |\n" +
                "----------------------\n";

        canvas.drawShape(rectangle);
        String actual = canvas.render();

        assertEquals(expected, actual);
    }


    @Test
    public void fillShouldFillEmptyArea() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 6);
        Line line1 = new Line(1, 3, 20, 3);
        Line line2 = new Line(3, 1, 3, 6);
        String expected =
                "----------------------\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "----------------------\n";

        canvas.drawShape(line1);
        canvas.drawShape(line2);
        canvas.fill(5, 1, 'y');
        String actual = canvas.render();

        assertEquals(expected, actual);
    }

    @Test
    public void fillShouldFillColoredArea() {
        ConsoleCanvas canvas = new ConsoleCanvas(20, 6);
        Line line1 = new Line(1, 3, 20, 3);
        Line line2 = new Line(3, 1, 3, 6);

        canvas.drawShape(line1);
        canvas.drawShape(line2);

        // color with y
        String expected1 =
                "----------------------\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "----------------------\n";
        canvas.fill(5, 1, 'y');
        String actual = canvas.render();
        assertEquals(expected1, actual);

        // color with x
        String expected2 =
                "----------------------\n" +
                "|  xxxxxxxxxxxxxxxxxx|\n" +
                "|  xxxxxxxxxxxxxxxxxx|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "----------------------\n";
        canvas.fill(5, 2, 'x');
        actual = canvas.render();
        assertEquals(expected2, actual);

        // color with z
        String expected3 =
                "----------------------\n" +
                "|  zzzzzzzzzzzzzzzzzz|\n" +
                "|  zzzzzzzzzzzzzzzzzz|\n" +
                "|zzzzzzzzzzzzzzzzzzzz|\n" +
                "|  z                 |\n" +
                "|  z                 |\n" +
                "|  z                 |\n" +
                "----------------------\n";
        canvas.fill(5, 3, 'z');
        actual = canvas.render();
        assertEquals(expected3, actual);
    }

    @Test
    public void fillLargeArea() {
        int timeThresholdMs = 500;
        int width = 1000;
        int height = 1000;
        ConsoleCanvas canvas = new ConsoleCanvas(width, height);

        long start = System.currentTimeMillis();
        canvas.fill(5, 1, 'y');
        long stop = System.currentTimeMillis();
        System.out.printf("Fill area %sx%s takes: %dms", width, height, stop - start);

        assertTrue("fill large area takes longer than " + timeThresholdMs + " ms",
                stop - start < timeThresholdMs);
    }
}
