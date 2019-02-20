package com.zuhlke.cs;

import java.util.*;

public class Canvas {

    public final static char EMPTY_PIXEL = ' ';

    private final char[][] pixels;
    private final int width;
    private final int height;

    public Canvas(int width, int height) {
        if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Minimum size of canvas is 2x2");
        }
        this.width = width;
        this.height = height;
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

    public void drawRectangle(Rectangle rectangle) {
        rectangle.getLines()
                .forEach(this::drawLine);
    }

    public void drawLine(Line line) {
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getColor());
    }

    public void drawLine(final int x1, final int y1, final int x2, final int y2, char c) {
        if (x1 == x2) { // vertical line
            if (x1 > width || x1 < 1) {
                return; // not visible
            }
            // cut line if too long
            int yStart = y1 < 1 ? 1 : y1;
            final int yEnd = y2 > height ? height : y2;
            // draw line
            for (; yStart <= yEnd; yStart++) {
                pixels[x1][yStart] = c;
            }
        } else if (y1 == y2) { // horizontal line
            if (y1 > height || y1 < 1) {
                return; // not visible
            }
            // cut line if too long
            int xStart = x1 < 1 ? 1 : x1;
            final int xEnd = x2 > width ? width : x2;
            // draw line
            for (; xStart <= xEnd; xStart++) {
                pixels[xStart][y1] = c;
            }
        }
    }

    public void fill(int x, int y, char color) {
        if (x < 1 || x > width || y < 1 || y > height) {
            return;
        }
        if (pixels[x][y] != EMPTY_PIXEL) {
            return; // we can fill only empty space
        }

        LinkedList<Pixel> queue = new LinkedList<>();
        pixels[x][y] = color;
        Pixel source = new Pixel(x, y);
        queue.add(source);

        while (!queue.isEmpty()) {
            Pixel next = queue.poll();
            colorEmptyNeighborsAndAddToQueue(next, color, queue);
        }
    }

    private void colorEmptyNeighborsAndAddToQueue(Pixel pixel, char color, Queue<Pixel> queue) {
        int x = pixel.x;
        int y = pixel.y;

        //left
        if (x - 1 > 0 && pixels[x - 1][y] == EMPTY_PIXEL) {
            pixels[x - 1][y] = color;
            queue.add(new Pixel(x - 1, y));
        }
        //right
        if (x + 1 <= width && pixels[x + 1][y] == EMPTY_PIXEL) {
            pixels[x + 1][y] = color;
            queue.add(new Pixel(x + 1, y));
        }
        //up
        if (y - 1 > 0 && pixels[x][y - 1] == EMPTY_PIXEL) {
            pixels[x][y - 1] = color;
            queue.add(new Pixel(x, y - 1));
        }
        //down
        if (y + 1 <= height && pixels[x][y + 1] == EMPTY_PIXEL) {
            pixels[x][y + 1] = color;
            queue.add(new Pixel(x, y + 1));
        }
    }

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
