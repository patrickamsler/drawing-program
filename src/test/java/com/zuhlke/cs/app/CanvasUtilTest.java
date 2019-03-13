package com.zuhlke.cs.app;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CanvasUtilTest {

    @Test
    public void deepCopy2dArray() {
        // given
        char[][] arr = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};

        // when
        char[][] actual = CanvasUtil.deepCopyArray(arr);

        // then
        assertEquals(2, arr.length);
        assertEquals(3, arr[0].length);

        assertNotEquals(arr[0], actual[0]);

        assertEquals(arr[0][0], 'a');
        assertEquals(arr[0][1], 'b');
        assertEquals(arr[0][2], 'c');
        assertEquals(arr[1][0], 'd');
        assertEquals(arr[1][1], 'e');
        assertEquals(arr[1][2], 'f');
    }

}
