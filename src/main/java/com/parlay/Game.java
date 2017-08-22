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

    public boolean equals(Game other) {
        return
            (this.lhTeam == other.lhTeam || this.lhTeam.equals(other.lhTeam)) &&
            (this.lhSpread == other.lhSpread || this.lhSpread.equals(other.lhSpread)) &&
            (this.rhTeam == other.rhTeam || this.rhTeam.equals(other.rhTeam)) &&
            (this.rhSpread == other.rhSpread || this.rhSpread.equals(other.rhSpread)) &&
            (this.over == other.over || this.over.equals(other.over)) &&
            (this.under == other.under || this.under.equals(other.under)) &&
            (this.parlayType == other.parlayType || this.parlayType.equals(other.parlayType)) &&
            this.week == other.week;
    }
}
