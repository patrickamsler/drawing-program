package com.zuhlke.cs.app;

import com.zuhlke.cs.model.Line;
import com.zuhlke.cs.model.Rectangle;

import java.util.Scanner;

public class CanvasApplication {

    private final static String CMD_PROMPT = "enter command: ";

    private ConsoleCanvas canvas;

    public static void main(String... args) {
        CanvasApplication canvasApplication = new CanvasApplication();
        canvasApplication.run(new Scanner(System.in));
    }

    void run(Scanner scanner) {
        while (true) {
            System.out.print(CMD_PROMPT);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            } else if (input.toUpperCase().startsWith("Q")) {
                break; // end the drawing program
            }
            try {
                parseInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // we use package default in order to
    // access the method from the unit tests
    void parseInput(String input) {
        char cmd = input.toUpperCase().charAt(0);
        String args[] = input.split("\\s+");
        if (cmd != 'C' && canvas == null) {
            throw new IllegalArgumentException("Create new canvas first. E.g. 'C 4 5'");
        }
        switch (cmd) {
            case 'C':
                throwExceptionForIllegalNumOfArgs(input, 2);
                int canvasWidth = parseInt(args[1]);
                int canvasHeight = parseInt(args[2]);
                canvas = new ConsoleCanvas(canvasWidth, canvasHeight);
                canvas.print();
                break;
            case 'L':
                throwExceptionForIllegalNumOfArgs(input, 4);
                Line line = new Line(parseInt(args[1]), parseInt(args[2]), parseInt(args[3]), parseInt(args[4]));
                canvas.drawShape(line);
                canvas.print();
                break;
            case 'R':
                throwExceptionForIllegalNumOfArgs(input, 4);
                Rectangle rectangle = new Rectangle(parseInt(args[1]), parseInt(args[2]), parseInt(args[3]), parseInt(args[4]));
                canvas.drawShape(rectangle);
                canvas.print();
                break;
            case 'B':
                throwExceptionForIllegalNumOfArgs(input, 3);
                canvas.fill(parseInt(args[1]), parseInt(args[2]), args[3].charAt(0));
                canvas.print();
                break;
            case 'U':
                canvas.undo();
                canvas.print();
                break;
            case 'E':
                canvas.redo();
                canvas.print();
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

    public ConsoleCanvas getCanvas() {
        return canvas;
    }

}
