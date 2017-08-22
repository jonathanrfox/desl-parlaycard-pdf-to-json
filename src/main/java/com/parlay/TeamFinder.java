package com.parlay;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.similarity.JaroWinklerDistance;


public class TeamFinder {

    private static final JaroWinklerDistance distance = new JaroWinklerDistance();
    private static List<String> teams = new ArrayList<>(Arrays.asList(new String[]
        {
            "PANTHERS",
            "BRONCOS",
            "FALCONS",
            "BUCCANEERS",
            "VIKINGS",
            "TITANS",
            "EAGLES",
            "BROWNS",
            "BENGALS",
            "JETS",
            "SAINTS",
            "RAIDERS",
            "CHIEFS",
            "CHARGERS",
            "RAVENS",
            "BILLS",
            "TEXANS",
            "BEARS",
            "PACKERS",
            "JAGUARS",
            "SEAHAWKS",
            "DOLPHINS",
            "COWBOYS",
            "GIANTS",
            "COLTS",
            "LIONS",
            "CARDINALS",
            "PATRIOTS",
            "STEELERS",
            "REDSKINS",
            "RAMS",
            "49ERS"
        }));

    public static String find(String team) {
        double maxScore = 0.0;
        int bestMatchIdx = 0;

        // find maxiumum score
        for (int i = 0; i < teams.size(); i++) {
            double score = distance.apply(teams.get(i), team);
            if (score > maxScore) {
                maxScore = score;
                bestMatchIdx = i;
            }
        }

        // set arbitrary threshold
        if (maxScore < 0.65) {
            // log the actual vs bestMatch: warning
            return null;
        }

        // log the actual vs bestMatch: success

        // pop found element off the list to reduce search space and prevent dupes
        String bestMatch = teams.get(bestMatchIdx);
        teams.remove(bestMatchIdx);
        return bestMatch;
    }
}
