package com.zuhlke.cs.model;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final char color;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, 'x');
    }

    public Rectangle(int x1, int y1, int x2, int y2, char color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public List<Line> getLines() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(x1, y1, x2, y1, color));
        lines.add(new Line(x2, y1, x2, y2, color));
        lines.add(new Line(x2, y2, x1, y2, color));
        lines.add(new Line(x1, y2, x1, y1, color));
        return lines;
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
