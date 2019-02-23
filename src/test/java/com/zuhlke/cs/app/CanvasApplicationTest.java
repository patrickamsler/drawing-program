package com.zuhlke.cs.app;

import com.zuhlke.cs.Canvas;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class CanvasApplicationTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private CanvasApplication app;

    @Before
    public void setUp() {
        Canvas canvasMock = Mockito.mock(Canvas.class);
        app = new CanvasApplication();
        app.setCanvas(canvasMock);
    }

    @Test
    public void drawLineThrowsExceptionForIllegalArgument() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid number of arguments");

        app.parseInput("L 1 2 3");
    }

    @Test
    public void drawLineThrowsExceptionIfNaN() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number expected!");

        app.parseInput("L 1 2 3 w");
    }

    @Test
    public void unknownCommandThrowsException() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown command: 'F'");

        app.parseInput("F 1 1");
    }
}
