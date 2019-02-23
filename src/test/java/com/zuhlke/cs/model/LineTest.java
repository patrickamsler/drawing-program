package com.zuhlke.cs.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LineTest {

    @Test
    public void createHorizontalLine() {
        Line line = new Line(0, 0, 42, 0);
        assertLineEquals(line, 0, 0, 42, 0);
    }

    @Test
    public void createHorizontalLineReverse() {
        Line line = new Line(42, 0, 0, 0);
        assertLineEquals(line, 0, 0, 42, 0);
    }

    @Test
    public void createVerticalLine() {
        Line line = new Line(0, 0, 0, 42);
        assertLineEquals(line, 0, 0, 0, 42);
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

    @Test
    public void cutLinesLongerThanBounds() {
        Line line1 = new Line(2, 1, 2, 42);
        Line line2 = new Line(1, 2, 42, 2);
        Rectangle bounds = new Rectangle(1, 1, 5, 5);

        Line cut1 = line1.cut(bounds);
        Line cut2 = line2.cut(bounds);

        assertLineEquals(cut1, 2, 1, 2, 5);
        assertLineEquals(cut2, 1, 2, 5, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDiagonalLineThrowsException() {
        new Line(0, 5, 3, 7);
    }

    private static void assertLineEquals(Line line, int x1, int y1, int x2, int y2) {
        assertEquals(new Line(x1, y1, x2, y2), line);
    }
}
