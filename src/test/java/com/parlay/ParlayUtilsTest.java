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
    public void shouldThrowArrayIndexOutOfBoundsExceptionWhenFindingIndex() {
        List<String> testList = Arrays.asList(new String[]{"Hello", "Hello"});
        Pattern pattern = Pattern.compile("wo.*");
        int actual = ParlayUtils.findIndex(testList, 0, pattern);
    }

    @Test
    public void shouldCleanText() {
        String testText = ""
            + " DENOTES HOME TEAM \n"
            + " PRO \n"
            + "abc-1\n"
            + " PRO \n"
            + "abc-2\n"
            + " Parlay Payoffs \n";
        List<String> actual = ParlayUtils.clean(testText);
        List<String> expected = Arrays.asList(new String[]{"abc-1", "abc-2"});
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldFindAll() {
        // String line = " 1 RA DER - 9½ ";
        String line = "abc-1 abc-2 abc-3";
        Pattern pattern = Pattern.compile("abc-\\d");
        List<String> actual = ParlayUtils.findAll(line, pattern);
        List<String> expected = Arrays.asList(new String[]{"abc-1", "abc-2", "abc-3"});
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
    public void shouldJoinAndReplaceWhitespace() {
        List<String> list = Arrays.asList(new String[]{" ", "a", "b ", " ", "c ", " - 1"});
        String actual = ParlayUtils.joinAndReplaceWhitespace(list);
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
        // String line = " 5 RAIDERS - 9½ 1:00pm 6 RAMS + 9½ ";
        String line = " 1 RAIDERS - 9½ ";
        String actual = ParlayUtils.parseSpread(line);
        String expected = "-9.5";
        assertEquals(actual, expected);
    }

    @Test
    public void shouldParseOver() {
        String line = " OAK/LA OVER 55½ ";
        String actual = ParlayUtils.parseOver(line);
        String expected =  "55.5";
        assertEquals(actual, expected);
    }

    @Test
    public void shouldParseUnder() {
        String line = " OAK/LA UNDER 55½ ";
        String actual = ParlayUtils.parseUnder(line);
        String expected =  "55.5";
        assertEquals(actual, expected);
    }
}
