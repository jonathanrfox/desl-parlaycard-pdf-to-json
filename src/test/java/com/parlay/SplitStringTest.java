package com.parlay;

import org.junit.Test;
import static org.junit.Assert.*;

import com.parlay.SplitString;


public class SplitStringTest {

    @Test
    public void shouldSplitStringOnChar() {
        String line = "abc:def";
        SplitString splitString = new SplitString(line, ':', 1);

        assertEquals(splitString.getLhs(), "abc");
        assertEquals(splitString.getRhs(), "def");
    }

    @Test
    public void shouldSplitStringAtHalfWayPoint() {
        String line = "abc     def";
        SplitString splitString = new SplitString(line, '?', 1);

        assertEquals(splitString.getLhs(), "abc");
        assertEquals(splitString.getRhs(), "def");
    }
}
