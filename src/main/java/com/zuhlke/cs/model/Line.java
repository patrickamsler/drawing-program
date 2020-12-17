package com.zuhlke.cs.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Line implements Shape {

    public static final char DEFAULT_LINE_COLOR = 'x';

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public Line(int x1, int y1, int x2, int y2) {
        if (!(x1 == x2 || y1 == y2)) {
            throw new IllegalArgumentException("Diagonal lines not possible");
        }
        // first coordinates always smaller then second
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
    }

    @Override
    public List<Line> getLines() {
        return Arrays.asList(new Line(x1, y1, x2, y2));
    }

    @Override
    public boolean intersects(Rectangle bounds) {
        if (x1 == x2 && // vertical line
                (x1 > bounds.getX2() || x2 < bounds.getX1())) {
            return false;
        }
        else if (y1 == y2 && // horizontal line
                (y1 > bounds.getY2() || y2 < bounds.getY1())) {
            return false;
        }
        return true;
    }

    public Line truncate(Rectangle bounds) {
        int xStart = Math.max(x1, bounds.getX1());
        int xEnd = Math.min(x2, bounds.getX2());
        int yStart = Math.max(y1, bounds.getY1());
        int yEnd = Math.min(y2, bounds.getY2());
        return new Line(xStart, yStart, xEnd, yEnd);
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public char getColor() {
        return DEFAULT_LINE_COLOR;
    }


    // override toString, equals and hashCode for testing purpose

    @Override
    public String toString() {
        return "Line{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return x1 == line.x1 &&
                y1 == line.y1 &&
                x2 == line.x2 &&
                y2 == line.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }
}
