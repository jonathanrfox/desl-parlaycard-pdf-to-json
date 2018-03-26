package parlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public static ParlayCard create(String parlayType) {
        return parlayType.equals("rev")
            ? new IrregularCard(parlayType)
            : new RegularCard(parlayType);
    }

    public abstract void consume(List<String> lines);
}
