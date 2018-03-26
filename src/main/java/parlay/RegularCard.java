package parlay;

import java.lang.Double;
import java.util.List;
import java.util.logging.Logger;
import java.util.Map;


public class RegularCard extends ParlayCard {

    private static Logger LOGGER = Logger.getLogger(RegularCard.class.getName());

    public RegularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size() - 1; i += 2) {
            LOGGER.info("Line[" + i + "]: " + lines.get(i));
            LOGGER.info("Line[" + (i + 1) + "]: " + lines.get(i + 1));

            Map<String, String> topLine = ParlayUtils.splitLine(lines.get(i), ':');
            String bottomLine = lines.get(i + 1);
            Game game = new Game();
            game.setHomeTeam(ParlayUtils.parseTeam(topLine.get("home")));
            game.setHomeSpread(ParlayUtils.parseSpread(topLine.get("home")));
            game.setAwayTeam(ParlayUtils.parseTeam(topLine.get("away")));
            game.setAwaySpread(ParlayUtils.parseSpread(topLine.get("away")));
            game.setOver(ParlayUtils.parseOver(bottomLine));
            game.setUnder(ParlayUtils.parseUnder(bottomLine));
            LOGGER.info(game.toString());
            super.addGame(game);
        }
    }
}
