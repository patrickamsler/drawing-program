package com.zuhlke.cs;

public class Canvas {

    private final char[][] pixels;
    private final int width;
    private final int height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new char[width + 2][height + 2];
        initPixels();
    }

    private void initPixels() {
        for (int y = 0; y < pixels[0].length; y++) {
            for (int x = 0; x < pixels.length; x++) {
                char c = ' ';
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

    private void drawLine(final int x1, final int y1, final int x2, final int y2, char c) {
        if (x1 == x2) { // vertical

        } else if (y1 == y2) { // horizontal
            int xStart = x1 < 1 ? 1 : x1;
            final int xEnd = x2 > width ? width : x2;
            for (; xStart <= xEnd; xStart++) {
                pixels[xStart][y1] = c;
            }
        }
    }

    public void paint() {
        for (int y = 0; y < pixels[0].length; y++) {
            for (int x = 0; x < pixels.length; x++) {
                System.out.print(pixels[x][y]);
            }
            System.out.println();
        }
    }
}
