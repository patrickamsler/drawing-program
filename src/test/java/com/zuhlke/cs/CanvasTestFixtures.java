package com.zuhlke.cs;

public class CanvasTestFixtures {

    static String emptyCanvas20x4() {
        return "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n"
                ;
    }

    static String canvas20x4WithHorizontalLine() {
        return "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n"
                ;
    }

    static String canvas20x4WithVerticalLine() {
        return "----------------------\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "| x                  |\n" +
                "----------------------\n"
                ;
    }

    static String canvas20x4WithRectangle() {
        return "----------------------\n" +
                "| xxx                |\n" +
                "| x x                |\n" +
                "| xxx                |\n" +
                "|                    |\n" +
                "----------------------\n"
                ;
    }

    static String canvas20x6WithFilledArea() {
        return "----------------------\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|  xyyyyyyyyyyyyyyyyy|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "----------------------\n"
                ;
    }

    static String canvas20x6WithFilledArea2() {
        return "----------------------\n" +
                "|  xxxxxxxxxxxxxxxxxx|\n" +
                "|  xxxxxxxxxxxxxxxxxx|\n" +
                "|xxxxxxxxxxxxxxxxxxxx|\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "|  x                 |\n" +
                "----------------------\n"
                ;
    }

    static String canvas20x6WithFilledArea3() {
        return "----------------------\n" +
                "|  zzzzzzzzzzzzzzzzzz|\n" +
                "|  zzzzzzzzzzzzzzzzzz|\n" +
                "|zzzzzzzzzzzzzzzzzzzz|\n" +
                "|  z                 |\n" +
                "|  z                 |\n" +
                "|  z                 |\n" +
                "----------------------\n"
                ;
    }
}
