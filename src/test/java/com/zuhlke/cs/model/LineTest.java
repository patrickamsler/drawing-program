package com.zuhlke.cs.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void createVerticalLine() {
        Line line = new Line(0, 0, 0, 42);

        assertEquals(0, line.getX1());
        assertEquals(0, line.getY1());
        assertEquals(0, line.getX2());
        assertEquals(42, line.getY2());
    }

    @Test
    public void intersectsReturnsTrueWithinBounds() {
        Line line1 = new Line(2, 1, 2, 5);
        Line line2 = new Line(1, 2, 5, 2);
        Rectangle bounds = new Rectangle(1, 1, 5, 5);

        assertTrue(line1.intersects(bounds));
        assertTrue(line2.intersects(bounds));
    }

    @Test
    public void intersectsReturnsFalseIfOutOfBounds() {
        Line line1 = new Line(6, 1, 6, 5);
        Line line2 = new Line(0, 1, 0, 5);
        Line line3 = new Line(1, 6, 5, 6);
        Line line4 = new Line(1, 0, 5, 0);
        Rectangle bounds = new Rectangle(1, 1, 5, 5);

        assertFalse(line1.intersects(bounds));
        assertFalse(line2.intersects(bounds));
        assertFalse(line3.intersects(bounds));
        assertFalse(line4.intersects(bounds));
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDiagonalLineThrowsException() {
        new Line(0, 5, 3, 7);
    }
}
