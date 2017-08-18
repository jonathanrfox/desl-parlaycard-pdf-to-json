package com.parlay;


public class Game {
    private String lhTeam;
    private String lhSpread;
    private String rhTeam;
    private String rhSpread;
    private String over;
    private String under;

    private int week;
    private String parlayType;

    public Game() {
        this.lhTeam = null;
        this.lhSpread = null;
        this.rhTeam = null;
        this.rhSpread = null;
        this.over = null;
        this.under = null;
        this.week = -1;
        this.parlayType = null;
    }

    public Game(String lhTeam, String lhSpread, String rhTeam, String rhSpread, String over, String under) {
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
}
