package com.parlay;

import java.util.ArrayList;
import java.util.List;

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

    public abstract void consume(List<String> lines);
}
