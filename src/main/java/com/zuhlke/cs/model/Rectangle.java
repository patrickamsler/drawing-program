package com.zuhlke.cs.model;

import com.zuhlke.cs.Shape;

import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Shape {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final char color;
    private final List<Line> lines = new ArrayList<>();

    public Rectangle(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, Line.DEFAULT_LINE_COLOR);
    }

    public Rectangle(int x1, int y1, int x2, int y2, char color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;

        lines.add(new Line(x1, y1, x2, y1, color));
        lines.add(new Line(x2, y1, x2, y2, color));
        lines.add(new Line(x2, y2, x1, y2, color));
        lines.add(new Line(x1, y2, x1, y1, color));
    }

    @Override
    public List<Line> getLines() {
        return new ArrayList<>(lines);
    }

    @Override
    public boolean intersects(Rectangle bounds) {
        return lines.stream()
                .anyMatch(line -> line.intersects(bounds));
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
}
