package com.parlay;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.parlay.Game;


abstract class ParlayCard {

    private final String TEAM_SPLIT_PATTERN = "\\d+:\\d+";
    private final Pattern TEAM_PATTERN = Pattern.compile("[0-9]{0,2}[A-Z]{3,}");

    private final String OVER_UNDER_SPLIT_PATTERN = "UNDER";
    private final Pattern SPREAD_PATTERN = Pattern.compile("[+-]\\s*\\d*½");

    private List<Game> games;
    private int week;
    private String parlayType;

    public ParlayCard(String parlayType) {
        this.parlayType = parlayType;
        this.games = new ArrayList<>();
        this.week = 0;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void addGame(Game game) {
        game.setWeek(this.week);
        game.setParlayType(this.parlayType);
        this.games.add(game);
    }

    private String findMatch(String s, Pattern p) {
        Matcher matcher = p.matcher(s);
        return matcher.find() ? matcher.group() : null;
    }

    public void parseTeamsAndSpreads(Game game, String line) {
        String[] split = line.split(TEAM_SPLIT_PATTERN, 2);
        String lhs = split[0], rhs = split[1];

        game.setLhTeam( findMatch(lhs, TEAM_PATTERN) );
        game.setLhSpread( findMatch(lhs, SPREAD_PATTERN) );

        game.setRhTeam( findMatch(rhs, TEAM_PATTERN) );
        game.setRhSpread( findMatch(rhs, SPREAD_PATTERN) );
    }

    public void parseOverUnder(Game game, String line) {
        String[] split = line.split(OVER_UNDER_SPLIT_PATTERN, 2);
        String lhs = split[0], rhs = split[1];

        game.setOver( findMatch(lhs, SPREAD_PATTERN) );
        game.setUnder( findMatch(rhs, SPREAD_PATTERN) );
    }

    public abstract void consume(List<String> lines);
}
