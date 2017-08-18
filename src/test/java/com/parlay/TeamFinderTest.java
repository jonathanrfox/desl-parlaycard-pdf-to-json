package com.parlay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import com.parlay.TeamFinder;


@RunWith(Parameterized.class)
public class TeamFinderTest {

    private String actual;
    private String expected;

    public TeamFinderTest(String actual, String expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"ATRIOTS", "PATRIOTS"},
                {"GIAN", "GIANTS"},
                {"GLES", "EAGLES"},
                {"CARDI", "CARDINALS"},
                {"49E", "49ERS"}
            });
    }

    @Test
    public void shouldFindTeam() {
        assertEquals(TeamFinder.find(this.actual), this.expected);
    }
}
