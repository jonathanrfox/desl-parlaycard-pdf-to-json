package com.parlay;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.Class;
import java.util.Arrays;
import java.util.List;

import com.parlay.ParlayCards;
import com.parlay.RegularCard;
import com.parlay.IrregularCard;


@RunWith(Parameterized.class)
public class ParlayCardsTest {

    private Class actualClass;
    private Class expectedClass;

    public ParlayCardsTest(String parlayType, Class expectedClass) {
        this.actualClass = ParlayCards.get(parlayType).getClass();
        this.expectedClass = expectedClass;
    }

    @Parameterized.Parameters
    public static List<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"std", RegularCard.class},
                {"tsr", RegularCard.class},
                {"sup", RegularCard.class},
                {"rev", IrregularCard.class}
            });
    }

    @Test
    public void shouldReturnCorrectInstance() {
        assertEquals(this.actualClass, this.expectedClass);
    }
}
