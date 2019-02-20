package com.zuhlke.cs;

public class CanvasTestFixtures {

    public static String emptyCanvas20x4() {
        return "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n"
                ;
    }

    public static String canvas20x4WithHorizontalLine() {
        return "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n"
                ;
    }

    public static String canvas20x4WithVerticalLine() {
        return "----------------------\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "----------------------\n"
                ;
    }

    public static String emptyCanvas20x4WithRectangle() {
        return "----------------------\n" +
                "| xxx                |\n" +
                "| x x                |\n" +
                "| xxx                |\n" +
                "|                    |\n" +
                "----------------------\n"
                ;
    }
}
