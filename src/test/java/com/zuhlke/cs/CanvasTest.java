package com.zuhlke.cs;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.zuhlke.cs.CanvasTestFixtures.*;

public class CanvasTest {

    @Test
    public void newCanvasCreatesEmptyDrawingAreaWithBorder() {
        Canvas canvas = new Canvas(20, 4);

        String output = canvas.render();

        assertEquals(emptyCanvas20x4(), output);
    }

    @Test
    public void drawHorizontalLine() {
        Canvas canvas = new Canvas(20, 4);

        canvas.drawLine(1, 2, 6, 2, 'x');
        String output = canvas.render();

        assertEquals(canvas20x4WithHorizontalLine(), output);
    }

    @Test
    public void drawVerticalLine() {
        Canvas canvas = new Canvas(20, 4);

        canvas.drawLine(2, 1, 2, 4, 'x');
        String output = canvas.render();

        assertEquals(canvas20x4WithVerticalLine(), output);
    }

    @Test
    public void drawLineLongerThanCanvas() {
        Canvas canvas = new Canvas(20, 4);

        canvas.drawLine(2, 0, 2, 42, 'x');
        String output = canvas.render();

        assertEquals(canvas20x4WithVerticalLine(), output);
    }

    @Test
    public void linesOutOfBoundsAreNotVisible() {
        Canvas canvas = new Canvas(20, 4);

        canvas.drawLine(1, 4, 4, 5, 'x');
        canvas.drawLine(42, 1, 42, 3, 'x');
        String output = canvas.render();

        assertEquals(emptyCanvas20x4(), output);
    }

    @Test
    public void drawRectangle() {
        Canvas canvas = new Canvas(20, 4);

        Rectangle rectangle = new Rectangle(2,1,4,3);
        canvas.drawRectangle(rectangle);
        String output = canvas.render();

        assertEquals(canvas20x4WithRectangle(), output);
    }


    @Test
    public void fillShouldFillEmptyArea() {
        Canvas canvas = new Canvas(20, 6);

        canvas.drawLine(1, 3, 20, 3, 'x');
        canvas.drawLine(3, 1, 3, 6, 'x');
        canvas.fill(5, 1, 'y');
        String output = canvas.render();

        assertEquals(canvas20x6WithFilledArea(), output);
    }
}
