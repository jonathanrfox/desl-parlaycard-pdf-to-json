package com.parlay;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import com.parlay.ParlayCards;
import com.parlay.RegularCard;
import com.parlay.IrregularCard;


@RunWith(Parameterized.class)
public class ParlayCardsTest {

    private String parlayType;
    private ParlayCard cardType;

    public ParlayCardsTest(String parlayType, ParlayCard cardType) {
        this.parlayType = parlayType;
        this.cardType = cardType;
    }

    @Parameterized.Parameters
    public static List<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"std", new RegularCard("std")},
                {"tsr", new RegularCard("tsr")},
                {"sup", new RegularCard("sup")},
                {"rev", new IrregularCard("rev")}
            });
    }

    @Test void shouldReturnCorrectInstance() {
        ParlayCard parlay = ParlayCards.get(this.parlayType);
        assertEquals(parlay.getClass(), this.cardType.getClass());
    }

    // @Test
    // public void shouldReturnInstanceOfRegularCardForStd() {
    //     ParlayCard parlay = ParlayCards.get("std");
    //     assertTrue(parlay instanceof RegularCard);
    // }

    // @Test
    // public void shouldReturnInstanceOfRegularCardForTsr() {
    //     ParlayCard parlay = ParlayCards.get("tsr");
    //     assertTrue(parlay instanceof RegularCard);
    // }

    // @Test
    // public void shouldReturnInstanceOfRegularCardForSup() {
    //     ParlayCard parlay = ParlayCards.get("sup");
    //     assertTrue(parlay instanceof RegularCard);
    // }

    // @Test
    // public void shouldReturnInstanceOfIrregularCardForRev() {
    //     ParlayCard parlay = ParlayCards.get("rev");
    //     assertTrue(parlay instanceof IrregularCard);
    // }
}
