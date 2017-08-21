package com.parlay;

import com.parlay.IrregularCard;
import com.parlay.ParlayCard;
import com.parlay.RegularCard;


public class ParlayCards {

    public static ParlayCard get(String parlayType) {
        // ArgumentParser verifies that parlayType be one of {"rev", "std", "tsr", "sup"}
        System.out.println(parlayType);
        return parlayType.equals("rev")
            ? new IrregularCard(parlayType)
            : new RegularCard(parlayType);
    }
}
