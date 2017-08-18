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
    public void shouldThrowArrayIndexOutOfBoundsException() {
        List<String> testList = Arrays.asList(new String[]{"Hello", "Hello"});
        Pattern pattern = Pattern.compile("wo.*");
        int index = ParlayUtils.findIndex(testList, 0, pattern);
    }

    @Test
    public void shouldCleanText() {
        String testText = ""
            + " DENOTES HOME TEAM \n"
            + " PRO \n"
            + "1\n"
            + " PRO \n"
            + "2\n"
            + " Parlay Payoffs \n";
        List<String> actual = ParlayUtils.clean(testText);
        List<String> expected = Arrays.asList(new String[]{"1", "2"});
        assertThat(actual, is(expected));
    }
}
