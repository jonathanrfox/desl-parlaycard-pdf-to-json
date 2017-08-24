package com.parlay;

import java.util.List;
import java.util.logging.Logger;

import com.parlay.Game;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;
import com.parlay.SplitString;


class IrregularCard extends ParlayCard {

    private static Logger LOGGER = Logger.getLogger(IrregularCard.class.getName());

    public IrregularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            LOGGER.info("Line[" + i + "]: " + lines.get(i));
            SplitString line = new SplitString(lines.get(i), ':', 1);

            String lhTeam = ParlayUtils.parseTeam(line.getLhs());
            String lhSpread = ParlayUtils.parseSpread(line.getLhs());

            String rhTeam = ParlayUtils.parseTeam(line.getRhs());
            String rhSpread = ParlayUtils.parseSpread(line.getRhs());

            Game game = new Game(lhTeam, lhSpread, rhTeam, rhSpread);
            LOGGER.info(game.toString());
            addGame(game);
        }
    }
}
