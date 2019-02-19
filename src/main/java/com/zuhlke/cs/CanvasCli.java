package com.zuhlke.cs;

import java.util.Arrays;
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
        if ((cmd == 'C' || cmd == 'Q') && canvas == null) {
            throw new IllegalArgumentException("Create new canvas first. E.g. 'C 4 5'");
        }
        switch (cmd) {
            case 'C':
                throwExceptionForIllegalNumOfArgs(input, 2);
                int canvasWidth = parseInt(args[1]);
                int canvasHeight = parseInt(args[2]);
                canvas = new Canvas(canvasWidth, canvasHeight);
                canvas.paint();
                break;
            case 'L':
                throwExceptionForIllegalNumOfArgs(input, 4);
                int lineX1 = parseInt(args[1]);
                int lineY1 = parseInt(args[2]);
                int lineX2 = parseInt(args[3]);
                int lineY2 = parseInt(args[4]);
                break;
            case 'R':
                throwExceptionForIllegalNumOfArgs(input, 4);
                int recX1 = parseInt(args[1]);
                int recY1 = parseInt(args[2]);
                int recX2 = parseInt(args[3]);
                int recY2 = parseInt(args[4]);
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
