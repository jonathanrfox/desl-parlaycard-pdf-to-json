package com.parlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parlay.Game;


abstract class ParlayCard {

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

    public static Map<String, String> splitLine(String line, char on) {
        line = line.trim();
        int maybeHp = line.indexOf(on);
        int hp = maybeHp >= 0 ? maybeHp : (int) line.length() / 2;

        Map<String, String> map = new HashMap<>();
        String lhs = line.substring(0, hp).trim();
        String rhs = line.substring(hp + 1).trim();

        if (lhs.contains("")) {
            map.put("home", lhs);
            map.put("away", rhs);
        } else {
            map.put("home", rhs);
            map.put("away", lhs);
        }

        return map;
    }

    public abstract void consume(List<String> lines);
}
