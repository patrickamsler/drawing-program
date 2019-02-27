package com.zuhlke.cs.app;

import com.zuhlke.cs.model.Line;
import com.zuhlke.cs.model.Shape;
import com.zuhlke.cs.model.Rectangle;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleCanvas {

    public final static char EMPTY_PIXEL = ' ';

    private final char[][] pixels;
    private final int width;
    private final int height;
    private final Rectangle drawingBounds;

    public ConsoleCanvas(int width, int height) {
        if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Minimum size of canvas is 2x2");
        }
        this.width = width;
        this.height = height;
        drawingBounds = new Rectangle(1, 1, width, height);
        pixels = new char[width + 2][height + 2];
        initPixels();
    }

    private void initPixels() {
        for (int y = 0; y < pixels[0].length; y++) {
            for (int x = 0; x < pixels.length; x++) {
                char c = EMPTY_PIXEL;
                if (y == 0 || y == pixels[0].length - 1) {
                    c = '-';
                } else if (x == 0 || x == pixels.length - 1) {
                    c = '|';
                }
                pixels[x][y] = c;
            }
        }
    }

    public void drawShape(Shape shape) {
        List<Line> drawableLines = shape.getLines().stream()
                .filter(line -> line.intersects(drawingBounds)) // we draw only lines that are visible in canvas
                .map(line -> line.truncate(drawingBounds)) // we truncate lines longer then canvas
                .collect(Collectors.toList());

        if (drawableLines.isEmpty()) {
            throw new IllegalArgumentException("Coordinates out of bounds!");
        }

        // we draw the visible part of the shape
        drawableLines
                .forEach(line -> drawLine(line.getX1(), line.getY1(),
                        line.getX2(), line.getY2(), line.getColor()));
    }

    private void drawLine(final int x1, final int y1, final int x2, final int y2, char c) {
        if (x1 == x2) { // vertical line
            // draw line
            for (int i = y1; i <= y2; i++) {
                pixels[x1][i] = c;
            }
        } else if (y1 == y2) { // horizontal line
            // draw line
            for (int i = x1; i <= x2; i++) {
                pixels[i][y1] = c;
            }
        }
    }

    public void fill(int x, int y, char fillColor) {
        if (x < 1 || x > width || y < 1 || y > height) {
            throw new IllegalArgumentException(" Coordinates out of bounds!");
        }
        char actualColor = pixels[x][y];
        if (actualColor == fillColor) {
            return;
        }

        LinkedList<Pixel> queue = new LinkedList<>();
        pixels[x][y] = fillColor;
        Pixel source = new Pixel(x, y);
        queue.add(source);

        // we use breadth first traversal to find the neighbors
        while (!queue.isEmpty()) {
            Pixel next = queue.poll();
            colorNeighborsAndAddToQueue(queue, next, fillColor, actualColor);
        }
    }

    private void colorNeighborsAndAddToQueue(Queue<Pixel> queue, Pixel pixel, char fillColor, char actualColor) {
        int x = pixel.x;
        int y = pixel.y;

        //left
        if (x - 1 > 0 && pixels[x - 1][y] == actualColor) {
            pixels[x - 1][y] = fillColor;
            queue.add(new Pixel(x - 1, y));
        }
        //right
        if (x + 1 <= width && pixels[x + 1][y] == actualColor) {
            pixels[x + 1][y] = fillColor;
            queue.add(new Pixel(x + 1, y));
        }
        //up
        if (y - 1 > 0 && pixels[x][y - 1] == actualColor) {
            pixels[x][y - 1] = fillColor;
            queue.add(new Pixel(x, y - 1));
        }
        //down
        if (y + 1 <= height && pixels[x][y + 1] == actualColor) {
            pixels[x][y + 1] = fillColor;
            queue.add(new Pixel(x, y + 1));
        }
    }

    // we use package default in order to
    // access the method from the unit tests
    String render() {
        StringBuilder buffer = new StringBuilder();

        for (int y = 0; y < pixels[0].length; y++) {
            for (int x = 0; x < pixels.length; x++) {
                buffer.append(pixels[x][y]);
            }
            buffer.append('\n');
        }

        return buffer.toString();
    }

    public void print() {
        System.out.println(render());
    }


    // private helper class
    private static class Pixel {
        final int x;
        final int y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pixel{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
