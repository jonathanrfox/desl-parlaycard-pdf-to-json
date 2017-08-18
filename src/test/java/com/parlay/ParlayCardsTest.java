package com.parlay;

import org.junit.Test;
import static org.junit.Assert.*;

import com.parlay.ParlayCards;
import com.parlay.RegularCard;
import com.parlay.IrregularCard;


public class ParlayCardsTest {

    @Test
    public void shouldReturnInstanceOfRegularCardForStd() {
        ParlayCard parlay = ParlayCards.get("std");
        assertTrue(parlay instanceof RegularCard);
    }

    @Test
    public void shouldReturnInstanceOfRegularCardForTsr() {
        ParlayCard parlay = ParlayCards.get("tsr");
        assertTrue(parlay instanceof RegularCard);
    }

    @Test
    public void shouldReturnInstanceOfRegularCardForSup() {
        ParlayCard parlay = ParlayCards.get("sup");
        assertTrue(parlay instanceof RegularCard);
    }

    @Test
    public void shouldReturnInstanceOfIrregularCardForRev() {
        ParlayCard parlay = ParlayCards.get("rev");
        assertTrue(parlay instanceof IrregularCard);
    }
}
