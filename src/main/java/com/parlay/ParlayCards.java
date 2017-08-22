package com.parlay;

import com.parlay.IrregularCard;
import com.parlay.ParlayCard;
import com.parlay.RegularCard;


public class ParlayCards {

    public static ParlayCard get(String parlayType) {
        return parlayType.equals("rev")
            ? new IrregularCard(parlayType)
            : new RegularCard(parlayType);
    }
}
