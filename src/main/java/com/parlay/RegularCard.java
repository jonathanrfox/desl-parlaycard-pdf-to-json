package com.parlay;

import java.util.List;
import java.util.logging.Logger;

import com.parlay.Game;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;
import com.parlay.SplitString;


public class RegularCard extends ParlayCard {

    private static Logger LOGGER = Logger.getLogger(RegularCard.class.getName());

    public RegularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size() - 1; i += 2) {
            LOGGER.info("Line[" + i + "]: " + lines.get(i));
            LOGGER.info("Line[" + (i + 1) + "]: " + lines.get(i + 1));

            SplitString top = new SplitString(lines.get(i), ':', 1);
            SplitString bottom = new SplitString(lines.get(i + 1), '½', 2);

            String lhTeam = ParlayUtils.parseTeam(top.getLhs());
            String lhSpread = ParlayUtils.parseSpread(top.getLhs());

            String rhTeam = ParlayUtils.parseTeam(top.getRhs());
            String rhSpread = ParlayUtils.parseSpread(top.getRhs());

            String over = ParlayUtils.parseOver(bottom.getLhs());
            String under = ParlayUtils.parseUnder(bottom.getRhs());

            Game game = new Game(lhTeam, lhSpread, rhTeam, rhSpread, over, under);
            LOGGER.info(game.toString());
            addGame(game);
        }
    }
}
