package com.parlay;

import java.lang.Double;
import java.util.List;
import java.util.logging.Logger;
import java.util.Map;

import com.parlay.Game;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;


public class RegularCard extends ParlayCard {

    private static Logger LOGGER = Logger.getLogger(RegularCard.class.getName());

    public RegularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size() - 1; i += 2) {
            LOGGER.info("Line[" + i + "]: " + lines.get(i));
            LOGGER.info("Line[" + (i + 1) + "]: " + lines.get(i + 1));

            Map<String, String> topLine = splitLine(lines.get(i), ':');
            String bottomLine = lines.get(i + 1);
            Game game = new Game(ParlayUtils.parseTeam(topLine.get("home")),
                                 ParlayUtils.parseSpread(topLine.get("home")),
                                 ParlayUtils.parseTeam(topLine.get("away")),
                                 ParlayUtils.parseSpread(topLine.get("away")),
                                 ParlayUtils.parseOver(bottomLine),
                                 ParlayUtils.parseUnder(bottomLine));

            LOGGER.info(game.toString());
            addGame(game);
        }
    }
}
