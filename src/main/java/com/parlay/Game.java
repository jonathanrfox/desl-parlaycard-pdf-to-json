package com.parlay;

import java.lang.Double;


public class Game {

    private String homeTeam;
    private Double homeSpread;
    private String awayTeam;
    private Double awaySpread;
    private Double over;
    private Double under;
    private String parlayType;
    private int week;

    public Game() {
        this.homeTeam = null;
        this.homeSpread = null;
        this.awayTeam = null;
        this.awaySpread = null;
        this.parlayType = null;
        this.over = null;
        this.under = null;
        this.week = -1;
    }

    public Game(String homeTeam, Double homeSpread, String awayTeam, Double awaySpread) {
        this();
        this.homeTeam = homeTeam;
        this.homeSpread = homeSpread;
        this.awayTeam = awayTeam;
        this.awaySpread = awaySpread;
    }

    public Game(String homeTeam, Double homeSpread, String awayTeam, Double awaySpread, Double over, Double under) {
        this(homeTeam, homeSpread, awayTeam, awaySpread);
        this.over = over;
        this.under = under;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setHomeSpread(Double homeSpread) {
        this.homeSpread = homeSpread;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setAwaySpread(Double awaySpread) {
        this.awaySpread = awaySpread;
    }

    public void setOver(Double over) {
        this.over = over;
    }

    public void setUnder(Double under) {
        this.under = under;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setParlayType(String parlayType) {
        this.parlayType = parlayType;
    }

    public String toString() {
        return ""
            + this.homeTeam + ", "
            + this.homeSpread + ", "
            + this.awayTeam + ", "
            + this.awaySpread + ", "
            + this.over + ", "
            + this.under + ", "
            + this.week;
    }

    // private boolean stringsEqual(String s, String o) {
    //     return s == o || s.equals(o);
    // }

    public boolean equals(Game other) {
        return
            this.homeTeam.equals(other.homeTeam) &&
            this.awayTeam.equals(other.awayTeam) &&
            this.parlayType.equals(other.parlayType) &&
            this.homeSpread == other.homeSpread &&
            this.awaySpread == other.awaySpread &&
            this.over == other.over &&
            this.under == other.under &&
            this.week == other.week;
    }
}
