package com.parlay;

import org.apache.commons.text.similarity.JaroWinklerDistance;


public class TeamFinder {

    private static final JaroWinklerDistance distance = new JaroWinklerDistance();
    private static final String[] teams = {
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
    };

    public static String find(String s) {
        double maxScore = 0.0;
        String bestMatch = null;

        for (String team : teams) {
            double score = distance.apply(team, s);
            if (score > maxScore) {
                maxScore = score;
                bestMatch = team;
            }
        }
        return bestMatch;
    }
}
