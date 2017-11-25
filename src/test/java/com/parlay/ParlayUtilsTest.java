package com.parlay;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.ArrayIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.parlay.ParlayUtils;


public class ParlayUtilsTest {

    @Test
    public void shouldFindIndex() {
        List<String> testList = Arrays.asList(new String[]{"Hello", "world"});
        Pattern pattern = Pattern.compile("wo.*");
        int actual = ParlayUtils.findIndex(testList, 0, pattern);
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void shouldThrowWhenFindingIndex() {
        List<String> testList = Arrays.asList(new String[]{"Hello", "Hello"});
        Pattern pattern = Pattern.compile("wo.*");
        int actual = ParlayUtils.findIndex(testList, 0, pattern);
    }

    @Test
    public void shouldCleanText() {
        String testText = ""
            + " DENOTES HOME TEAM \n"
            + " PRO \n"
            + " 1 abc\n"
            + " PRO \n"
            + " 2 def\n";
        List<String> actual = ParlayUtils.clean(testText);
        List<String> expected = Arrays.asList(new String[]{" 1 abc", " 2 def"});
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldFindOne() {
        String line = "abc-1 abc-2 abc-3";
        Pattern pattern = Pattern.compile("abc-\\d");
        String actual = ParlayUtils.findOne(line, pattern);
        String expected = "abc-1";
        assertEquals(actual, expected);
    }

    @Test
    public void shouldParseTeam() {
        String line = " 1 RAIDERS - 9½ ";
        String actual = ParlayUtils.parseTeam(line);
        String expected = "RAIDERS";
        assertEquals(actual, expected);
    }

    @Test
    public void shouldParseSpread() {
        String line = " 1 RAIDERS - 9½ ";
        double actual = ParlayUtils.parseSpread(line);
        double expected = -9.5;
        assertEquals(actual, expected, 0.01);
    }

    @Test
    public void shouldParseOver() {
        String line = " OAK/LA OVER 55½ ";
        double actual = ParlayUtils.parseOver(line);
        double expected =  55.5;
        assertEquals(actual, expected, 0.01);
    }

    @Test
    public void shouldParseUnder() {
        String line = " OAK/LA UNDER 55½ ";
        double actual = ParlayUtils.parseUnder(line);
        double expected =  55.5;
        assertEquals(actual, expected, 0.01);
    }
}
