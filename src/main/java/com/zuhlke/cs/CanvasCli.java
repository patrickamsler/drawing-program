package com.zuhlke.cs;

import java.util.Scanner;

public class CanvasCli {

    private Canvas canvas;

    public CanvasCli() {

    }

    public static void main(String... args) {
        CanvasCli canvasCli = new CanvasCli();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            try {
                canvasCli.parseInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void parseInput(String input) {
        char cmd = input.toUpperCase().charAt(0);
        String args[] = input.split("\\s+");
        if (cmd != 'C' && cmd != 'Q' && canvas == null) {
            throw new IllegalArgumentException("Create new canvas first. E.g. 'C 4 5'");
        }
        switch (cmd) {
            case 'C':
                throwExceptionForIllegalNumOfArgs(input, 2);
                int canvasWidth = parseInt(args[1]);
                int canvasHeight = parseInt(args[2]);
                canvas = new Canvas(canvasWidth, canvasHeight);
                canvas.print();
                break;
            case 'L':
                throwExceptionForIllegalNumOfArgs(input, 4);
                Line line = new Line(parseInt(args[1]), parseInt(args[2]), parseInt(args[3]), parseInt(args[4]));
                canvas.drawLine(line);
                canvas.print();
                break;
            case 'R':
                throwExceptionForIllegalNumOfArgs(input, 4);
                Rectangle rectangle = new Rectangle(parseInt(args[1]), parseInt(args[2]), parseInt(args[3]), parseInt(args[4]));
                canvas.drawRectangle(rectangle);
                canvas.print();
                break;
            case 'B':
                throwExceptionForIllegalNumOfArgs(input, 3);
                int width = parseInt(args[1]);
                int height = parseInt(args[2]);
                char color = args[3].charAt(0);
                break;
            case 'Q':
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown command: '" + cmd + "'");
        }
    }

    private static void throwExceptionForIllegalNumOfArgs(String input, int numberOfArgs) {
        String args[] = input.split("\\s+");
        if (args.length < numberOfArgs + 1) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

    private static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number expected!");
        }
    }
}
