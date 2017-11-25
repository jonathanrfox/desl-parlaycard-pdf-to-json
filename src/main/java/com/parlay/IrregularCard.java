package com.parlay;

import java.util.List;
import java.util.logging.Logger;
import java.util.Map;

import com.parlay.Game;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;


class IrregularCard extends ParlayCard {

    private static Logger LOGGER = Logger.getLogger(IrregularCard.class.getName());

    public IrregularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            LOGGER.info("Line[" + i + "]: " + lines.get(i));

            Map<String, String> line = ParlayUtils.splitLine(lines.get(i), ':');
            Game game = new Game(ParlayUtils.parseTeam(line.get("home")),
                                 ParlayUtils.parseSpread(line.get("home")),
                                 ParlayUtils.parseTeam(line.get("away")),
                                 ParlayUtils.parseSpread(line.get("away")));

            LOGGER.info(game.toString());
            addGame(game);
        }
    }
}
