package com.parlay;


public class Game {

    private String lhTeam;
    private String lhSpread;
    private String rhTeam;
    private String rhSpread;
    private String over;
    private String under;
    private String parlayType;
    private int week;

    public Game() {
        this.lhTeam = null;
        this.lhSpread = null;
        this.rhTeam = null;
        this.rhSpread = null;
        this.over = null;
        this.under = null;
        this.parlayType = null;
        this.week = -1;
    }

    public Game(String lhTeam, String lhSpread, String rhTeam, String rhSpread) {
        this();
        this.lhTeam = lhTeam;
        this.lhSpread = lhSpread;
        this.rhTeam = rhTeam;
        this.rhSpread = rhSpread;
    }

    public Game(String lhTeam, String lhSpread, String rhTeam, String rhSpread, String over, String under) {
        this();
        this.lhTeam = lhTeam;
        this.lhSpread = lhSpread;
        this.rhTeam = rhTeam;
        this.rhSpread = rhSpread;
        this.over = over;
        this.under = under;
    }

    public void setLhTeam(String lhTeam) {
        this.lhTeam = lhTeam;
    }

    public void setLhSpread(String lhSpread) {
        this.lhSpread = lhSpread;
    }

    public void setRhTeam(String rhTeam) {
        this.rhTeam = rhTeam ;
    }

    public void setRhSpread(String rhSpread) {
        this.rhSpread = rhSpread;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public void setUnder(String under) {
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
            + this.lhTeam + ", "
            + this.lhSpread + ", "
            + this.rhTeam + ", "
            + this.rhSpread + ", "
            + this.over + ", "
            + this.under + ", "
            + this.week;
    }

    private boolean stringsEqual(String s, String o) {
        return s == o || s.equals(o);
    }

    public boolean equals(Game other) {
        return
            stringEquals(this.lhTeam, other.lhTeam) &&
            stringEquals(this.lhSpread, other.lhSpread) &&
            stringEquals(this.rhTeam, other.rhTeam) &&
            stringEquals(this.rhSpread, other.rhSpread) &&
            stringEquals(this.over, other.over) &&
            stringEquals(this.under, other.under) &&
            stringEquals(this.parlayType, other.parlayType) &&
            this.week == other.week;
    }
}
