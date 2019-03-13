package com.zuhlke.cs.app;

public class CanvasUtil {

    public static char[][] deepCopyArray(char[][] arr) {
        char[][] copy = new char[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            char[] copy2 = new char[arr[i].length];
            System.arraycopy(arr[i], 0, copy2, 0, arr[i].length);
            copy[i] = copy2;
        }
        return copy;
    }
}
