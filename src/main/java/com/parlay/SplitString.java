package com.parlay;


public class SplitString {

    public String lhs;
    public String rhs;

    public SplitString(String line, char on, int count) {
        line = line.trim();

        int onCount = (int) line.chars().filter(c -> c == on).count();
        int hp = onCount == count ? line.indexOf(on) + 1 : (int) line.length() / 2;

        this.lhs = line.substring(0, hp);
        this.rhs = line.substring(hp);
    }
}
