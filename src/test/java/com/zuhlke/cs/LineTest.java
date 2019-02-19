package com.zuhlke.cs;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void createHorizontalLine() {
        Line line = new Line(0, 0, 42, 0);

        assertEquals(0, line.getX1());
        assertEquals(0, line.getY1());
        assertEquals(42, line.getX2());
        assertEquals(0, line.getY2());
    }

    @Test
    public void createHorizontalLineReverse() {
        Line line = new Line(42, 0, 0, 0);

        assertEquals(0, line.getX1());
        assertEquals(0, line.getY1());
        assertEquals(42, line.getX2());
        assertEquals(0, line.getY2());
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDiagonalLineThrowsException() {
        new Line(0, 5, 3, 7);
    }
}
