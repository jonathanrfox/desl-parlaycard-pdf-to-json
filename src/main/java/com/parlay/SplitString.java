package com.parlay;

import java.util.logging.Logger;


public class SplitString {

    private Logger LOGGER = Logger.getLogger(SplitString.class.getName());
    private String lhs;
    private String rhs;

    public SplitString(String line, char on, int count) {
        line = line.trim();

        int onCount = (int) line.chars().filter(c -> c == on).count();
        int hp = onCount == count
            ? line.indexOf(on)
            : (int) line.length() / 2;

        this.lhs = line.substring(0, hp).trim();
        this.rhs = line.substring(hp + 1).trim();

        LOGGER.info("Left Hand String: " + this.lhs);
        LOGGER.info("Right Hand String: " + this.rhs);
    }

    public String getLhs() {
        return this.lhs;
    }

    public String getRhs() {
        return this.rhs;
    }
}
